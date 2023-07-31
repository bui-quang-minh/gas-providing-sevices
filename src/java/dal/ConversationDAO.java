/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Conversation;
import model.Customer;
import model.Employee;
import model.Messages;

/**
 *
 * @author admin
 */
public class ConversationDAO extends DBContext {

    public ArrayList<Customer> getCustomerByTime() {
        ArrayList<Customer> customers = new ArrayList<>();

        try {
            String sql = "SELECT c.customerID, c.firstName, c.lastName, c.customerImage, cv.conversationID\n"
                    + "FROM Customer c\n"
                    + "JOIN Conversations cv ON c.customerID = cv.customerID\n"
                    + "JOIN Messages m ON cv.conversationID = m.conversationID\n"
                    + "GROUP BY c.customerID, c.firstName, c.lastName, cv.conversationID,c.customerImage\n"
                    + "ORDER BY MAX(m.sentTime) DESC";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Customer c = new Customer();
                c.setId(rs.getInt("customerID"));
                c.setFirstname(rs.getString("firstName"));
                c.setLastname(rs.getString("lastName"));
                byte[] imageCustomer = rs.getBytes("customerImage");
                c.setImage(imageCustomer);
                if (imageCustomer != null) {
                    String encodedImage = Base64.getEncoder().encodeToString(imageCustomer);
                    c.setEncodedImage(encodedImage);
                }

                Conversation co = new Conversation();
                co.setId(rs.getInt("conversationID"));

                c.setConversation(co);

                customers.add(c);

            }
        } catch (SQLException ex) {
            Logger.getLogger(ConversationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return customers;
    }

    public ArrayList<Messages> getRecentMessages() {
        ArrayList<Messages> messages = new ArrayList<>();

        String sql = "SELECT m.content, c.conversationID,m.senderRole\n"
                + "FROM Conversations c\n"
                + "LEFT JOIN Messages m ON c.conversationID = m.conversationID\n"
                + "WHERE m.sentTime = (\n"
                + "  SELECT MAX(sentTime)\n"
                + "  FROM Messages\n"
                + "  WHERE conversationID = c.conversationID\n"
                + ")\n"
                + "ORDER BY c.startTime DESC";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Messages m = new Messages();
                m.setContent(rs.getString("content"));
                m.setSenderRole(rs.getString("senderRole"));

                Conversation co = new Conversation();
                co.setId(rs.getInt("conversationID"));
                m.setConversation(co);

                messages.add(m);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConversationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return messages;
    }

    public ArrayList<Messages> getMessagesConversation(int id) {

        ArrayList<Messages> messages = new ArrayList<>();

        String sql = "SELECT conv.conversationID, c.firstName, c.lastName, c.customerImage, e.firstName as emFirstName, e.lastName as emLastName, e.employeeImage, m.content, m.sentTime, m.senderRole\n"
                + "FROM Conversations conv\n"
                + "JOIN Customer c ON conv.customerID = c.customerID\n"
                + "JOIN Conversation_Employee ce ON conv.conversationID = ce.conversationID\n"
                + "JOIN Employee e ON ce.employeeID = e.employeeID\n"
                + "JOIN Messages m ON conv.conversationID = m.conversationID AND \n"
                + "             ((m.senderRole = 'Customer' AND m.senderID = c.customerID) OR\n"
                + "             (m.senderRole = 'Employee' AND m.senderID = e.employeeID))\n"
                + "WHERE conv.conversationID = ? \n"
                + "order by m.sentTime asc";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Messages m = new Messages();
                m.setContent(rs.getString("content"));
                m.setSentTime(rs.getTimestamp("sentTime"));
                m.setSenderRole(rs.getString("senderRole"));

                Conversation co = new Conversation();
                co.setId(rs.getInt("conversationID"));
                m.setConversation(co);

                Customer c = new Customer();
                c.setFirstname(rs.getString("firstName"));
                c.setLastname(rs.getString("lastName"));
                byte[] imageCustomer = rs.getBytes("customerImage");
                c.setImage(imageCustomer);
                if (imageCustomer != null) {
                    String encodedImage = Base64.getEncoder().encodeToString(imageCustomer);
                    c.setEncodedImage(encodedImage);
                }
                m.setCustomer(c);

                Employee e = new Employee();
                e.setFirstname(rs.getString("emFirstName"));
                e.setLastname(rs.getString("emLastName"));
                byte[] imageEmployee = rs.getBytes("employeeImage");
                e.setImage(imageEmployee);
                if (imageEmployee != null) {
                    String encodedImage = Base64.getEncoder().encodeToString(imageEmployee);
                    e.setEncodedImage(encodedImage);
                }
                m.setEmployee(e);

                messages.add(m);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ConversationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return messages;
    }

    public void sendEmployeeMessage(Messages m) {
        try {
            ArrayList<Integer> employeeids = new ArrayList<>();
            String sql_check = "select employeeID from Conversation_Employee where conversationID = ?";
            PreparedStatement stm = connection.prepareStatement(sql_check);
            stm.setInt(1, m.getConversation().getId());
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                employeeids.add(rs.getInt("employeeID"));
            }
            if (!employeeids.contains(m.getSenderId())) {
                String sql_insert_employee = "INSERT INTO Conversation_Employee(conversationID, employeeID) VALUES(?,?)";
                PreparedStatement stm1 = connection.prepareStatement(sql_insert_employee);
                stm1.setInt(1, m.getConversation().getId());
                stm1.setInt(2, m.getSenderId());
                stm1.executeUpdate();
            }
            String sql_insert_messages = "INSERT INTO [Messages]\n"
                    + "([senderID],[senderRole],[content],[conversationID])\n"
                    + "VALUES (?,?,?,?)";
            PreparedStatement stm2 = connection.prepareStatement(sql_insert_messages);
            stm2.setInt(1, m.getSenderId());
            stm2.setString(2, m.getSenderRole());
            stm2.setString(3, m.getContent());
            stm2.setInt(4, m.getConversation().getId());
            stm2.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ConversationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void sendAutoEmployeeMessage(int conversationID) {
        try {
            ArrayList<Integer> employeeids = new ArrayList<>();
            String sql_check = "select employeeID from Conversation_Employee where conversationID = ?";
            PreparedStatement stm = connection.prepareStatement(sql_check);
            stm.setInt(1, conversationID);
            ResultSet rs = stm.executeQuery();
            int i = 0;
            while (rs.next()) {
                i++;
                employeeids.add(rs.getInt("employeeID"));
            }
            if (i == 0) {

                String sql_insert_employee = "INSERT INTO Conversation_Employee(conversationID, employeeID) VALUES(?,?)";
                PreparedStatement stm1 = connection.prepareStatement(sql_insert_employee);
                stm1.setInt(1, conversationID);
                stm1.setInt(2, 1);
                stm1.executeUpdate();

                String sql_insert_messages = "INSERT INTO [Messages]\n"
                        + "([senderID],[senderRole],[content],[conversationID])\n"
                        + "VALUES (?,?,?,?)";
                PreparedStatement stm2 = connection.prepareStatement(sql_insert_messages);
                stm2.setInt(1, 1);
                stm2.setString(2, "Employee");
                stm2.setString(3, "Welcome to Betrolimex! What can I help you?");
                stm2.setInt(4, conversationID);
                stm2.executeUpdate();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConversationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void sendCustomerMessage(Messages m) {
        try {
            String sql_insert_messages = "INSERT INTO [Messages]\n"
                    + "([senderID],[senderRole],[content],[conversationID])\n"
                    + "VALUES (?,?,?,?)";
            PreparedStatement stm2 = connection.prepareStatement(sql_insert_messages);
            stm2.setInt(1, m.getSenderId());
            stm2.setString(2, m.getSenderRole());
            stm2.setString(3, m.getContent());
            stm2.setInt(4, m.getConversation().getId());
            stm2.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ConversationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public int getConversationID(Customer c) {
        try {
            String sql = "Select * from Conversations where customerID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, c.getId());
            ResultSet rs = stm.executeQuery();
            return rs.getInt("conversationID");
        } catch (SQLException ex) {
            Logger.getLogger(ConversationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public static void main(String[] args) {
        ConversationDAO cb = new ConversationDAO();
        CustomerDAO cub = new CustomerDAO();
        Customer c = cub.getCustomer("tauhzit213", "Tauhzit213@");
        Messages m = new Messages();
        m.setSenderRole("Customer");
        m.setSenderId(c.getId());
        m.setContent("message");
        Conversation co = new Conversation();
        co.setId(c.getConversation().getId());
        m.setConversation(co);
        System.out.println(m.getConversation().getId());
        cb.sendCustomerMessage(m);
        System.out.println(cub.getCustomer("tauhzit213", "Tauhzit213@").getConversation().getId());
    }

}
