package org.example;

import org.example.NotificationCreators.AppNotificationCreator;
import org.example.NotificationCreators.EmailNotificationCreator;
import org.example.NotificationCreators.SMSNotificationCreator;
import org.example.NotificationTypes.Notification;


public class NotificationManager {
    private static NotificationManager instance;
    private String user;
    private String message;

    private NotificationManager() {}

    public static NotificationManager getInstance() {
        if (instance == null) {
            instance = new NotificationManager();
        }
        return instance;
    }

    public void configure(String user, String message) {
        this.user = user;
        this.message = message;
    }

    public void sendNotification(String type) {
        Notification notification;
        try {
            if ("email".equals(type)) {
                notification = new EmailNotificationCreator().createNotification();
            } else if ("sms".equals(type)) {
                notification = new SMSNotificationCreator().createNotification();
            } else if ("app".equals(type)) {
                notification = new AppNotificationCreator().createNotification();
            } else {
                throw new IllegalArgumentException("Invalid notification type: " + type);
            }
            notification.send(this.user, this.message);
        } catch (Exception e) {
            System.err.println("Error creating notification: " + e.getMessage());
        }

    }
}