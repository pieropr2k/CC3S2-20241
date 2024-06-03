

```java
public class NotificationManager {
    private String user;
    private String message;
    public NotificationManager(String user, String message) {
        this.user = user;
        this.message = message;
    }
    public void sendNotification(String type) {
        if ("email".equals(type)) {
            System.out.println("Enviando email a " + user + " con mensaje: " + message);
        } else if ("sms".equals(type)) {
            System.out.println("Enviando sms a " + user + " con mensaje: " + message);
        } else if ("app".equals(type)) {
            System.out.println("Enviando notificación de app a " + user + " con mensaje: " + message);
        }
    }
}
```

```java
public class NotificationManager {
    private static NotificationManager instance;
    private String user;
    private String message;

    private NotificationManager() {}

    public NotificationManager getInstance() {
        if (instance == null) {
           instance = new NotificationManager();
        }
        return instance;
    }
    
    public configure(String user, String message) {
        this.user = user;
        this.message = message;
    }

    public void sendNotification(String type) {
        Notification notification;
        if ("email".equals(type)) {
            notification = new EmailNotification();
        } else if ("sms".equals(type)) {
            notification = new SMSNotification();
        } else if ("app".equals(type)) {
            notification = new AppNotification();   
        }
        notification.send(this.user, this.message);   
    }
}

public class NotificationCreator {
    abstract public void createNotification();
    
    public void sendNotification(String user, String message) {
        Notification notification = createNotification();
        notification.send(user, message);
    }   
}

public class EmailNotificationCreator extends NotificationCreator {
    public void createNotification(){
        return new EmailNotification();
    }
}

public class SMSNotificationCreator extends NotificationCreator {
    public void createNotification(){
        return new SMSNotification();
    }
}

public class AppNotificationCreator extends NotificationCreator {
    public void createNotification(){
        return new AppNotification();
    }
}

public interface Notification {
    void send(String user, String message);
}

public class EmailNotification implements Notification {
    @Override
    public void send(String user, String message) {
        System.out.println("Enviando email a " + this.user + " con mensaje: " + message);
    }
}

public class SMSNotification implements ENotification {
    @Override
    public void send(String user, String message) {
        System.out.println("Enviando sms a " + user + " con mensaje: " + message);
    }
}

public class AppNotification implements Notification {
    @Override
    public void send(String user, String message) {
        System.out.println("Enviando notificación de app a " + user + " con mensaje: " + message);
    }
}
```
























































```java
public class NotificationManager {
    private String user;
    private String message;
    
    private List<EventListener> listeners = new ArrayList<>();

    public NotificationManager(String user, String message) {
        this.user = user;
        this.message = message;
    }

    public void subscribe(EventListener listener) {
        listeners.add(listener);
    }

    public void unsubscribe(EventListener listener) {
        listeners.remove(listener);
    }
    
    public void notify(String type) {
        for (EventListener listener: listeners){
            listener.sendNotification(this.user, this.message);
        }
    }
}



public interface EventListener {
    void sendNotification(String article);
}

public class EmailAlertsListener implements EventListener {
    @Override
    public void sendNotification(String user, String message) {
        System.out.println("Enviando email a " + this.user + " con mensaje: " + message);
    }
}

public class SMSAlertsListener implements EventListener {
    @Override
    public void sendNotification(String user, String message) {
        System.out.println("Enviando sms a " + user + " con mensaje: " + message);
    }
}

public class AppAlertsListener implements EventListener {
    @Override
    public void sendNotification(String user, String message) {
        System.out.println("Enviando notificación de app a " + user + " con mensaje: " + message);
    }
}
```