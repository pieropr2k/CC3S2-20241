package org.example.NotificationTypes;

public class SMSNotification implements Notification {
    @Override
    public void send(String user, String message) {
        System.out.println("Enviando sms a " + user + " con mensaje: " + message);
    }
}
