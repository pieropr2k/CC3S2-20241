package org.example.NotificationCreators;

import org.example.NotificationTypes.EmailNotification;
import org.example.NotificationTypes.Notification;

public class EmailNotificationCreator extends NotificationCreator {
    public Notification createNotification(){
        return new EmailNotification();
    }
}