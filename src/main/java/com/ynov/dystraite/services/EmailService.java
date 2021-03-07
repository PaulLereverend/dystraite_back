package com.ynov.dystraite.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Service
public class EmailService {

    @Value(value = "${mail.auth}")
    private String auth;

    @Value(value = "${mail.tls}")
    private String tls;

    @Value(value = "${mail.host}")
    private String host;

    @Value(value = "${mail.port}")
    private String port;

    @Value(value = "${mail.sender}")
    private String sender;

    @Value(value = "${mail.password}")
    private String password;

    public void sendMail(String recipient, String title, String content) {
        try{
            Properties props = new Properties();
            props.put("mail.smtp.auth", auth);
            props.put("mail.smtp.starttls.enable", tls);
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.port", port);

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
