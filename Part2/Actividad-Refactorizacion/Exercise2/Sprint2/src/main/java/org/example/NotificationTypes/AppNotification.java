package org.example.NotificationTypes;

public class AppNotification implements Notification {
    @Override
    public void send(String user, String message) {
        System.out.println("Enviando notificación de app a " + user + " con mensaje: " + message);
    }
}