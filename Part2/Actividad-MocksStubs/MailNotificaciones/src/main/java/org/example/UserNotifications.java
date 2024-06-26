package org.example;

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
