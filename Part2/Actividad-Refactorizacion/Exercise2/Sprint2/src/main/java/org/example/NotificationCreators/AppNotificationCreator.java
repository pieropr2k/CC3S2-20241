package org.example.NotificationCreators;

import org.example.NotificationTypes.AppNotification;
import org.example.NotificationTypes.Notification;

public class AppNotificationCreator extends NotificationCreator {
    public Notification createNotification(){
        return new AppNotification();
    }
}
