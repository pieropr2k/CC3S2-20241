package org.example;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;

import java.util.Properties;

public class RealMailServer implements MailServer{
    String username;
    String password;
    String smtpHost;
    int port;

    public RealMailServer(String username, String password, String smtpHost, int port){
        this.username = username;
        this.password = password;
        this.smtpHost = smtpHost;
        this.port = port;
    }

    @Override
    public void sendMail(String receiver, String subject, String messageContent) {
        System.out.println("TLSEmail Start");

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", true);
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", smtpHost);
        properties.put("mail.smtp.port", String.valueOf(port));
        properties.put("mail.smtp.ssl.trust", smtpHost);

        // crea el objeto Authenticator para pararlo en Session.getInstance()
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message mimeMsg = new MimeMessage(session);
            mimeMsg.setFrom(new InternetAddress(username));
            mimeMsg.setRecipient(Message.RecipientType.TO, new InternetAddress(receiver));
            mimeMsg.setSubject(subject);
            // Le damos formato
            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setContent(messageContent, "text/html; charset=utf-8");
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mimeBodyPart);
            mimeMsg.setContent(multipart);
            Transport.send(mimeMsg);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
