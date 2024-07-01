# Actividad Mocks y Stubs

## Ejercicio 1: Simulador de lanzamiento de dados

#### Paso 1

Crear una interfaz NumerosAleatorios que defina un método para obtener números
aleatorios dentro de un rango específico.

```java
// Paso 1: Crear una interfaz dados.NumerosAleatorios que defina un método para obtener números
//aleatorios dentro de un rango específico.

public interface NumerosAleatorios {
    int nextInt(int upperBoundExclusive);
}
```

#### Paso 2

Modificar la clase LanzamientoDados para que utilice la interfaz NumerosAleatorios,
inyectando la dependencia a través del constructor.

```java
// Paso 2: Modificar la clase LanzamientoDados para que utilice la interfaz NumerosAleatorios,
// inyectando la dependencia a través del constructor.

public class LanzamientoDados {
    private final int NUMERO_LADOS = 6;
    private final NumerosAleatorios rnd;

    public LanzamientoDados(NumerosAleatorios numerosAleatorios) {
        this.rnd = numerosAleatorios;
    }

    public int lanzar() {
        return rnd.nextInt(NUMERO_LADOS) + 1;
    }
}

```

#### Paso 3:
Desarrollar un stub de NumerosAleatorios para usar en pruebas unitarias, permitiendo controlar los resultados de los lanzamientos.

```java
public class LanzamientoDadosTest {
    @Test
    void produceMensaje(){
        // Arrange
        StubNumerosAleatorios stub = new StubNumerosAleatorios();
        int nextIntBound = 3;
        stub.setNextInt(nextIntBound);
        // Act
        LanzamientoDados play = new LanzamientoDados(stub);
        int attempt = play.attempt();
        // Assert
        assertThat(attempt).isEqualTo(nextIntBound+1);
    }
}
```
Creamos el stub de la clase `NumeroAleatorios`:
```java
/*
Paso 3: Desarrollar un stub de NumerosAleatorios para usar en pruebas unitarias, permitiendo
controlar los resultados de los lanzamientos.
* */
// Creamos el stub
public class StubNumerosAleatorios implements NumerosAleatorios{
    int result;

    public void setNextInt(int newValue){
        this.result = newValue;
    }

    @Override
    public int nextInt(int upperBound) {
        return this.result;
    }
}
```

Creamos el stub de la interfaz NumerosAleatorios, este stub lo creamos para que se controle el resultado numero aleatorio para nuestras pruebas unitarias y este dependa de los parametros que pongamos. En este caso le ponemos como nextInt 3 y se tiene que devolver 4.

#### Paso 4: 
Escribir pruebas unitarias para LanzamientoDados utilizando el stub para asegurar que la lógica del lanzamiento funciona como se espera bajo condiciones controladas.

```java
@ParameterizedTest
@ValueSource(ints = {0, 1, 2, 3, 4, 5})
void lanzarDadosConDiferentesResultados(int resultadoEsperado) {
    // Arrange
    StubNumerosAleatorios stub = new StubNumerosAleatorios();
    stub.setNextInt(resultadoEsperado);
    LanzamientoDados play = new LanzamientoDados(stub);
    // Act
    int resultado = play.attempt();
    // Assert
    assertThat(resultado).isEqualTo(resultadoEsperado + 1);
}
```

Tambien podemos usar Mockito:

```java
package dados;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

/*
Paso 3: Desarrollar un stub de NumerosAleatorios para usar en pruebas unitarias, permitiendo
controlar los resultados de los lanzamientos.
* */

@ExtendWith(MockitoExtension.class)
public class LanzamientoDadosMockitoTest {
    // usaremos mockito
    @Mock
    private NumerosAleatoriosStub stub;

    @InjectMocks
    private LanzamientoDados play;

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4, 5})
    void multipleAttemptsWithMockito(int resultadoEsperado) {
        // Arrange
        // hace que *cuando* se invoque a linea con stub.nextInt(6) se retorne *resultadoEsperado*, por esto usamos *when*
        when(stub.nextInt(6)).thenReturn(resultadoEsperado);
        //Act
        // dentro del metodo attempt() se llamara a la linea de arriba, asi que el when hará su trabajo
        int resultado = play.attempt();
        //Assert
        assertThat(resultado).isEqualTo(resultadoEsperado + 1);
    }
}
```
Se usa @Mock para inicializar el mock y @InjectMocks para inyectar el mock al objeto LanzamientoDados.

Una vez realizado esto dentro de *multipleAttemptsWithMockito()*, 
llamamos a *when()* de Mockito el cual actua **cuando** se invoca a linea con stub.nextInt(6) 
y hace que se retorne *resultadoEsperado* al invocar esta linea,
dentro del metodo attempt() se llamara a la linea `stub.nextInt(6)`, asi que when hará su trabajo aqui.

![](img/mockito.png)

#### Paso 5: 
Implementar una clase NumerosGeneradosAleatoriamente que utilice un generador de números aleatorios real y que cumpla con la interfaz NumerosAleatorios.

```java
public class NumerosAleatoriosAleatoriamente implements NumerosAleatorios{
    private final Random rnd = new Random();
    @Override
    public int nextInt(int upperBound) {
        return rnd.nextInt(upperBound);
    }
}
```
#### Paso 6
Integrar y probar la clase LanzamientoDados en una aplicación de producción, inyectando la implementación real de NumerosAleatorios.

```java
/*
 Paso 6: Integrar y probar la clase LanzamientoDados en una aplicación de producción,
inyectando la implementación real de NumerosAleatorios.
* */
public class Main {
    public static void main(String[] args) {
        NumerosAleatorios numerosAleatorios = new NumerosGeneradosAleatoriamente();
        LanzamientoDados lanzamientoDados = new LanzamientoDados(numerosAleatorios);
        System.out.println("Primer Lanzamiento:");
        System.out.println(lanzamientoDados.lanzar());
        System.out.println("Segundo Lanzamiento:");
        System.out.println(lanzamientoDados.lanzar());
        System.out.println("Tercer Lanzamiento:");
        System.out.println(lanzamientoDados.lanzar());
        System.out.println("Cuarto Lanzamiento:");
        System.out.println(lanzamientoDados.lanzar());
    }
}
```

![](img/produccion.png)

## Ejercicio 2:  Sistema de notificaciones por email

#### Paso 1

Definir una interfaz MailServer que abstraiga el envío de correos electrónicos.

```java
interface MailServer {
    void sendMail(String receiver, String subject, String message);
}
```
#### Paso 2

Crear una clase UserNotifications que dependa de la interfaz MailServer para enviar correos.

```java
public class UserNotifications{
    private final MailServer mailServer;

    public UserNotifications(MailServer mailServer) {
        this.mailServer = mailServer;
    }

    public void notifyUser(String receiver, String subject, String message) {
        if (receiver == null || receiver.isEmpty() || subject == null || subject.isEmpty() || message == null || message.isEmpty())
            throw new IllegalArgumentException("Los campos no pueden estar vacios");

        this.mailServer.sendMail(receiver, subject, message);
    }
}

```

#### Paso 3
Implementar un mock de MailServer que registre las llamadas a su método sendEmail y capture los valores de los parámetros enviados.

```java
@ExtendWith(MockitoExtension.class)
public class UserNotificationsTest {
    @Mock
    private MailServer mailServer;

    @InjectMocks
    private UserNotifications userNotifications;

    // tests
}
```

### Paso 4: 

Escribir pruebas unitarias para UserNotifications utilizando el mock para verificar que los correos se envíen correctamente.

```java
@ExtendWith(MockitoExtension.class)
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
```

### Paso 5: 

Implementar una clase RealMailServer que use SMTP para enviar correos en un entorno de producción.

```java
public class RealMailServer implements MailServer{
    String username;
    String password;
    String smtpHost;
    int port;

    public RealMailServer(String username, String password, String smtpHost, int port){
        this.username = username;
        this.password = password;
        this.smtpHost = smtpHost;
        this.port = port;
    }

    @Override
    public void sendMail(String receiver, String subject, String messageContent) {
        System.out.println("TLSEmail Start");

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", true);
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", smtpHost);
        properties.put("mail.smtp.port", String.valueOf(port));
        properties.put("mail.smtp.ssl.trust", smtpHost);

        // crea el objeto Authenticator para pararlo en Session.getInstance()
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message mimeMsg = new MimeMessage(session);
            mimeMsg.setFrom(new InternetAddress(username));
            mimeMsg.setRecipient(Message.RecipientType.TO, new InternetAddress(receiver));
            mimeMsg.setSubject(subject);
            // Le damos formato
            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setContent(messageContent, "text/html; charset=utf-8");
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mimeBodyPart);
            mimeMsg.setContent(multipart);
            Transport.send(mimeMsg);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
```

### Paso 6: 

Integrar UserNotifications en una aplicación de producción, inyectando la implementación real de MailServer.

```java
public class Main {
    public static void main(String[] args) {
        String username = SecretValues.senderMail; //requires valid gmail id
        String password = SecretValues.password; // correct password for gmail app id
        String toEmail = SecretValues.toEmail; // can be any email id
        String smtpHost = "smtp.gmail.com";

        int port = 587;
        RealMailServer server = new RealMailServer(username, password, smtpHost, port);
        UserNotifications userNotifications = new UserNotifications(server);
        for (int i = 0; i < 2; i++) {
            userNotifications.notifyUser(toEmail, SecretValues.subject, SecretValues.message);
        }
    }
}
```

![](img/mail_produccion.png)
