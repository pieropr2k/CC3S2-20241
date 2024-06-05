import org.example.NotificationManager;
import org.junit.jupiter.api.Test;

public class NotificationManagerTest {
    @Test
    void testEmailMessage() {
        String user = "donatelo";
        String message = "hola ps";
        NotificationManager notificationManager = new NotificationManager(user, message);
        notificationManager.sendNotification("email");
        //assertThat(words).isEqualTo("Enviando email a " + user + " con mensaje: " + message);
    }
}
