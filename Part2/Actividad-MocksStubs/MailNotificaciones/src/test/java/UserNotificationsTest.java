import org.example.MailServer;
import org.example.UserNotifications;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension
        .class)
public class UserNotificationsTest {
    @Mock
    private MailServer mailServer;

    @InjectMocks
    private UserNotifications userNotifications;

    @Test
    void notifyUserSendsEmail(){
        // Arrange
        String recipient = "piero.pilco.js@gmail.com";
        String subject = "Test";
        String message = "This is a test";
        // Act
        userNotifications.notifyUser(recipient, subject, message);
        // Assert
        Mockito.verify(mailServer).sendMail(recipient, subject, message);
    }
}
