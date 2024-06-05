package org.example.NotificationTypes;

public class EmailNotification implements Notification {
    @Override
    public void send(String user, String message) {
        System.out.println("Enviando email a " + user + " con mensaje: " + message);
    }
}