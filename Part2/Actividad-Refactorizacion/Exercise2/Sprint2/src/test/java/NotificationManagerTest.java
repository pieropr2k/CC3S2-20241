import org.example.NotificationManager;
import org.junit.jupiter.api.Test;

public class NotificationManagerTest {
    @Test
    void testEmailMessage() {
        String user = "donatelo";
        String message = "hola ps";
        NotificationManager manager = NotificationManager.getInstance();
        manager.configure("usuario@example.com", "Este es un mensaje de prueba");

        manager.sendNotification("email");
        //assertThat(words).isEqualTo("Enviando email a " + user + " con mensaje: " + message);
    }
}
