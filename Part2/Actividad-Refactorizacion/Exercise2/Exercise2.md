# Ejercicio 2

Se pide refactorizar este codigo mediante el uso de minimo 2 patrones de diseño.
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
Este codigo estara en la carpeta Sprint 1.

Usaremos 2 patrones: Factory y Singleton para refactorizar todo el codigo.

### Patron Factory
Notification sera nuestra interfaz Producto con la cual es común a todos los objetos que puede producir la clase Creadora (NotificationCreator) y sus subclases.

```java
public interface Notification {
    void send(String user, String message);
}

public class EmailNotification implements Notification {
    @Override
    public void send(String user, String message) {
        System.out.println("Enviando email a " + this.user + " con mensaje: " + message);
    }
}

public class SMSNotification implements Notification {
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

NotificationCreator sera nuestra clase Creadora con la cual tendremos el metodo *createNotification()* el cual devuelve nuevos objetos comunes a Notification y *sendNotification()* tiene hace la logica de negocio, donde se depende de createNotification(). Dicha clase sera heredada por sus subclases EmailNotificationCreator, SMSNotificationCreator y AppNotificationCreator.

```java
public abstract class NotificationCreator {
    abstract public Notification createNotification();

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
```

### Patron Singleton
Esta clase no tiene un constructor publico por lo que la única manera de obtener su objeto es invocando el método *getInstance()*
Dicho metodo permite a los clientes acceder a la misma instancia de NotificacionesManager.
El metodo *sendNotification()* tiene la logica de negocio.

```java
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
```
