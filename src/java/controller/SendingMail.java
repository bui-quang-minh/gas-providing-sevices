/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dal.CustomerDAO;
import dal.ProductDAO;
import java.time.Duration;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import model.Customer;
import model.Discount;
import model.Product;

/**
 *
 * @author minh
 */
public class SendingMail {

    String from = "swpgroup6@gmail.com";
    String host = "smtp.gmail.com";
    Properties properties = System.getProperties();

    public SendingMail() {
    }
    
    public void Sendmail(String to, String content) {
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("swpgroup6@gmail.com", "qxhrasdnsdaylems");
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("This is the email subject");
            message.setText("This is your Pin Code: " + content);

            Transport.send(message);
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }

    public void SendNotification(String discountValue, String productID,Discount d) {
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("swpgroup6@gmail.com", "qxhrasdnsdaylems");
            }
        });

        CustomerDAO cd = new CustomerDAO();
        ProductDAO pd = new ProductDAO();
        List<Customer> listCustomer = cd.getAll();
        Product p = pd.getProductByID(productID);
        LocalDate localDate1 = d.getStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate localDate2 = d.getEndDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        
        // Calculate the difference in days
        long dateDiff = Duration.between(localDate1.atStartOfDay(), localDate2.atStartOfDay()).toDays();
        for (Customer c : listCustomer) {
            try {
                MimeMessage message = new MimeMessage(session);
                message.setFrom(new InternetAddress(from));
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(c.getEmail()));
                message.setSubject("Today discount! Up to " + discountValue + "% !!");
                message.setText("Hi " + c.getFirstname() + " " + c.getLastname() + ",\n" + "We would like to thank you for being our customer. We would like to offer you a " + discountValue + "% discount on "+p.getProductName()+". The discount will be expired in the next "+dateDiff+" days, don't miss out!!!\nSincerely\nSWP_GROUP6");
                Transport.send(message);
            } catch (MessagingException mex) {
                mex.printStackTrace();
            }
        }
    }
}
