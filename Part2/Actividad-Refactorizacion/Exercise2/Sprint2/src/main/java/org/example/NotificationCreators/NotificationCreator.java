package org.example.NotificationCreators;

import org.example.NotificationTypes.Notification;

public abstract class NotificationCreator {
    abstract public Notification createNotification();

    public void sendNotification(String user, String message) {
        Notification notification = createNotification();
        notification.send(user, message);
    }
}