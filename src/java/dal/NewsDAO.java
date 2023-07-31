/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Navigation;
import model.News;
import model.Product;

/**
 *
 * @author adm
 */
public class NewsDAO extends DBContext {

    public String addMainNews(News s) {
        String sql = "INSERT INTO [dbo].[News]\n"
                + "           ([newsTitle]\n"
                + "           ,[newsHeading]\n"
                + "           ,[newsImage]\n"
                + "           ,[newsContent]\n"
                + "           ,[createdBy]\n"
                + "           ,[modifiedBy]\n"
                + "           ,[navigationID]\n"
                + "           ,[newsStatus])\n"
                + "     VALUES (?,?,?,?,?,?,?,?)";

        try ( PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, s.getNewsTitle());
            st.setString(2, s.getNewsHeading());
            st.setBytes(3, s.getNewsImage());
            st.setString(4, s.getNewsContent());
            st.setInt(5, s.getCreatedBy());
            st.setInt(6, s.getModifiedBy());
            st.setInt(7, 5);
            st.setString(8, s.getNewsStatus());
            st.executeUpdate();
        } catch (SQLException e) {

            return e + "";
        }
        return sql;
    }

    public List<News> getAllNews() {
        List<News> list = new ArrayList<>();
        String sql = "SELECT [newsID]\n"
                + ",[newsTitle]\n"
                + ",[newsHeading]\n"
                + ",[newsImage]\n"
                + ",[newsContent]\n"
                + ",[createdDate]\n"
                + ",[createdBy]\n"
                + ",[modifiedDate]\n"
                + ",[modifiedBy]\n"
                + ",[newsStatus]\n"
                + ",[approvedDate]\n"
                + "FROM [dbo].[News]\n"
                + "order by newsID desc";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                News s = new News();
                s.setNewID(rs.getInt("newsID"));
                s.setNewsTitle(rs.getString("newsTitle"));
                s.setNewsHeading(rs.getString("newsHeading"));
                byte[] newsImage = rs.getBytes("newsImage");
                s.setNewsImage(newsImage);
                if (newsImage != null) {
                    String encodedImage = Base64.getEncoder().encodeToString(newsImage);
                    s.setEncodedImage(encodedImage);
                }
                s.setNewsContent(rs.getString("newsContent"));
                s.setCreatedDate(rs.getDate("createdDate"));
                s.setCreatedBy(rs.getInt("createdBy"));
                s.setModifiedDate(rs.getDate("modifiedDate"));
                s.setModifiedBy(rs.getInt("modifiedBy"));
                s.setNewsStatus(rs.getString("newsStatus"));
                s.setApprovedDate(rs.getDate("approvedDate"));
                list.add(s);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public News getNewsByID(String NewID) {
        News s = new News();
        String sql = "SELECT [newsID]\n"
                + "      ,[newsTitle]\n"
                + "      ,[newsHeading]\n"
                + "      ,[newsImage]\n"
                + "      ,[newsContent]\n"
                + "      ,[createdDate]\n"
                + "      ,[createdBy]\n"
                + "      ,[modifiedDate]\n"
                + "      ,[modifiedBy]\n"
                + "      ,[newsStatus]\n"
                + "      ,[approvedDate]\n"
                + "  FROM [dbo].[News] where newsID = " + NewID + "";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {

                s.setNewID(rs.getInt("newsID"));
                s.setNewsTitle(rs.getString("newsTitle"));
                s.setNewsHeading(rs.getString("newsHeading"));
                byte[] newsImage = rs.getBytes("newsImage");
                s.setNewsImage(newsImage);
                if (newsImage != null) {
                    String encodedImage = Base64.getEncoder().encodeToString(newsImage);
                    s.setEncodedImage(encodedImage);
                }
                s.setNewsContent(rs.getString("newsContent"));
                s.setCreatedDate(rs.getDate("createdDate"));
                s.setCreatedBy(rs.getInt("createdBy"));
                s.setModifiedDate(rs.getDate("modifiedDate"));
                s.setModifiedBy(rs.getInt("modifiedBy"));
                s.setNewsStatus(rs.getString("newsStatus"));
                s.setApprovedDate(rs.getDate("approvedDate"));

            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return s;
    }

    public List<String> getNewsContentList() throws SQLException {
        List<String> newsContentList = new ArrayList<>();
        String sql = "SELECT newsContent FROM News";

        try ( PreparedStatement statement = connection.prepareStatement(sql);  ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                String filePath = resultSet.getString("newsContent");
                File file = new File(filePath);
                if (file.exists()) {
                    StringBuilder content = new StringBuilder();
                    try ( BufferedReader reader = new BufferedReader(new FileReader(file))) {
                        String line;
                        while ((line = reader.readLine()) != null) {
                            content.append(line);
                        }
                    }
                    newsContentList.add(content.toString());
                } else {
                    System.out.println("File không tồn tại: " + filePath);
                }
            }
        } catch (Exception e) {
            System.out.println("Lỗi truy vấn cơ sở dữ liệu: " + e.getMessage());
        }

        return newsContentList;
    }

    public List<News> getPendingNews() {
        List<News> list = new ArrayList<>();
        String sql = "SELECT [newsID]\n"
                + "      ,[newsTitle]\n"
                + "      ,[newsHeading]\n"
                + "      ,[newsImage]\n"
                + "      ,[newsContent]\n"
                + "      ,[createdDate]\n"
                + "      ,[createdBy]\n"
                + "      ,[modifiedDate]\n"
                + "      ,[modifiedBy]\n"
                + "      ,[newsStatus]\n"
                + "      ,[approvedDate]\n"
                + "  FROM [dbo].[News]\n"
                + "  WHERE [newsStatus]='Pending'\n";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                News s = new News();
                s.setNewID(rs.getInt("newsID"));
                s.setNewsTitle(rs.getString("newsTitle"));
                s.setNewsHeading(rs.getString("newsHeading"));
                byte[] newsImage = rs.getBytes("newsImage");
                s.setNewsImage(newsImage);
                if (newsImage != null) {
                    String encodedImage = Base64.getEncoder().encodeToString(newsImage);
                    s.setEncodedImage(encodedImage);
                }
                s.setNewsContent(rs.getString("newsContent"));
                s.setCreatedDate(rs.getDate("createdDate"));
                s.setCreatedBy(rs.getInt("createdBy"));
                s.setModifiedDate(rs.getDate("modifiedDate"));
                s.setModifiedBy(rs.getInt("modifiedBy"));
                s.setNewsStatus(rs.getString("newsStatus"));
                s.setApprovedDate(rs.getDate("approvedDate"));
                list.add(s);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<News> getNewbyNavigationID(int id) {
        List<News> list = new ArrayList<>();
        String sql = "SELECT *\n"
                + "  FROM [dbo].[News]\n"
                + "  where [navigationID] =" + id;
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                News s = new News();
                s.setNewID(rs.getInt("newsID"));
                s.setNewsTitle(rs.getString("newsTitle"));
                s.setNewsHeading(rs.getString("newsHeading"));
                byte[] newsImage = rs.getBytes("newsImage");
                s.setNewsImage(newsImage);
                if (newsImage != null) {
                    String encodedImage = Base64.getEncoder().encodeToString(newsImage);
                    s.setEncodedImage(encodedImage);
                }
                s.setNewsContent(rs.getString("newsContent"));
                s.setCreatedDate(rs.getDate("createdDate"));
                s.setCreatedBy(rs.getInt("createdBy"));
                s.setModifiedDate(rs.getDate("modifiedDate"));
                s.setModifiedBy(rs.getInt("modifiedBy"));
                s.setNewsStatus(rs.getString("newsStatus"));
                s.setApprovedDate(rs.getDate("approvedDate"));
                s.setNavigationID(rs.getInt("navigationID"));
                s.setParentID(rs.getInt("parentID"));
                list.add(s);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public void updateNews(News news) throws SQLException {
        String sql = "UPDATE [dbo].[News]\n"
                + "   SET [newsHeading] = ?\n"
                + "      ,[newsImage] = ?\n"
                + "      ,[newsContent]= ?\n"
                + "      ,[newsTitle] = ?\n"
                + " WHERE newsID=?";
        PreparedStatement st = connection.prepareStatement(sql);
        st.setString(1, news.getNewsHeading());
        st.setBytes(2, news.getNewsImage());
        st.setString(3, news.getNewsContent());
        st.setString(4, news.getNewsTitle());
        st.setInt(5, news.getNewID());
        st.execute();
    }

    public void deleteNewbyID(int id) {
        String sql = "DELETE FROM [dbo].[News]\n"
                + "      WHERE [News].[newsID]=" + id;
        String sql1 = "DELETE FROM [dbo].[News]\n"
                + "      WHERE [News].[parentID]=" + id;
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            PreparedStatement st1 = connection.prepareStatement(sql1);
            st1.execute();
            st.execute();

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void addnews(String newsTitle, String newsHeading, String newsContent, int navigationID, int user) {
        String sql = "INSERT INTO [dbo].[News] ([newsTitle], [newsHeading], [newsImage], [newsContent], [createdBy], [modifiedBy], [newsStatus], [navigationID]) VALUES (?, ?, null, ?, ?, ?, null, ?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, newsTitle);
            st.setString(2, newsHeading);
            st.setString(3, newsContent);
            st.setInt(4, user);
            st.setInt(5, user);
            st.setInt(6, navigationID);
            st.execute();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void addbanner(String newsTitle, String newsHeading, byte[] img, String newsContent, int user, int navigationID) {
        String sql = "INSERT INTO [dbo].[News]\n"
                + "           ([newsTitle]\n"
                + "           ,[newsHeading]\n"
                + "           ,[newsImage]\n"
                + "           ,[newsContent]\n"
                + "           ,[createdBy]\n"
                + "           ,[modifiedBy]\n"
                + "           ,[navigationID]\n"
                + "           )\n"
                + "     VALUES(?,?,?,?,?,?,?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, newsTitle);
            st.setString(2, newsHeading);
            st.setBytes(3, img);
            st.setString(4, newsContent);
            st.setInt(5, user);

            st.setInt(6, user);
            st.setInt(7, navigationID);
            st.execute();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public String updateNewsInfo(News n) {

        String sql = "UPDATE [dbo].[News]\n"
                + "   SET [newsTitle] = ? \n"
                + "      ,[newsHeading] = ? \n"
                + "      ,[newsContent] = ? \n"
                + "      ,[newsStatus] = ? \n"
                + "      ,[modifiedDate] = ? \n"
                + " WHERE newsID = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            LocalDate date = LocalDate.now();
            Date formatDate = Date.valueOf(date);
            st.setString(1, n.getNewsTitle());
            st.setString(2, n.getNewsHeading());
            st.setString(3, n.getNewsContent());
            st.setString(4, n.getNewsStatus());
            st.setDate(5, formatDate);
            st.setInt(6, n.getNewID());
            st.executeUpdate();
        } catch (SQLException e) {
            return e + "";
        }
        return "ok";
    }

    public void deleteNews(int id) {
        String sql_delete_news = "DELETE FROM News\n"
                + "WHERE NewsID = ?";

        try {
            PreparedStatement stm = connection.prepareStatement(sql_delete_news);
            stm.setInt(1, id);
            stm.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(NewsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addnewwithparent(String title, String heading, String content, int navigationID, int userid, int parent) {
        String sql = "INSERT INTO [dbo].[News] ([newsTitle], [newsHeading], [newsImage], [newsContent],"
                + " [createdBy], [modifiedBy], [newsStatus], [navigationID], "
                + "[parentID]) VALUES (?, ?, null, ?, ?, ?, null, ?, ?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, title);
            st.setString(2, heading);
            st.setString(3, content);
            st.setInt(4, userid);
            st.setInt(5, userid);
            st.setInt(6, navigationID);
            st.setInt(7, parent);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    public List<Navigation> getAllNavigation() {
        List<Navigation> list = new ArrayList<>();
        String sql = "SELECT *\n"
                + "FROM dbo.Navigation\n"
                + "WHERE NavigationID <> 5;";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new Navigation(
                        rs.getInt("NavigationID"),
                        rs.getString("navigationName"),
                        rs.getString("navigationDescription"),
                        rs.getInt("createdBy"),
                        rs.getDate("createdDate"),
                        rs.getInt("modifiedBy"),
                        rs.getDate("modifiedDate")));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

}
