package org.example.NotificationCreators;
import org.example.NotificationTypes.Notification;
import org.example.NotificationTypes.SMSNotification;

public class SMSNotificationCreator extends NotificationCreator {
    public Notification createNotification(){
        return new SMSNotification();
    }
}