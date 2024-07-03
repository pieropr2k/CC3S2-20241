package org.example;

import org.example.data_server.DataServer;
import org.junit.jupiter.api.Test;

public class DataServerTest {
    @Test
    public void testSendMail() {
        DataServer dataServer = new DataServer(-6, 0.5, 470);
    }
}
