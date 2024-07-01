package org.example;

public class Main {
    public static void main(String[] args) {
        String username = SecretValues.senderMail; //requires valid gmail id
        String password = SecretValues.password; // correct password for gmail app id
        String toEmail = SecretValues.toEmail; // can be any email id
        String smtpHost = "smtp.gmail.com";

        int port = 587;
        RealMailServer server = new RealMailServer(username, password, smtpHost, port);
        UserNotifications userNotifications = new UserNotifications(server);
        for (int i = 0; i < 2; i++) {
            userNotifications.notifyUser(toEmail, SecretValues.subject, SecretValues.message);
        }
    }
}