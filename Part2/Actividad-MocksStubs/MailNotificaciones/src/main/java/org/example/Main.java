package org.example;

public class Main {
    public static void main(String[] args) {
        String username = "piero.pilco.py@gmail.com"; //requires valid gmail id
        String password = "ofrl vtjl vyee rvij"; // correct password for gmail app id
        String toEmail = "piero.pilco.js@gmail.com"; // can be any email id
        String smtpHost = "smtp.gmail.com";
        int port = 587;
        RealMailServer server = new RealMailServer(username, password, smtpHost, port);
        UserNotifications userNotifications = new UserNotifications(server);
        userNotifications.notifyUser(toEmail, "Probando", "El mensaje lo tienes que recibir");
    }
}