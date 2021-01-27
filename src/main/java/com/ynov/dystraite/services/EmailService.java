package com.ynov.dystraite.services;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.util.Properties;

public class EmailService {
    public static void sendMail(String recipient, String title, String content) {
        try{
            final String sender = "matteo.lecuit.dev@gmail.com";
            final String password = "wfbvdhbfuwtvhkli";

            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");

            Session session = Session.getDefaultInstance(props,
                    new javax.mail.Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(sender,password);
                        }
                    });

                MimeMessage message = new MimeMessage(session);
                message.setFrom(new InternetAddress(sender));
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
                message.setSubject(title);
                message.setText(content);
                Transport.send(message);
                System.out.println("Mail successfully sent");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
