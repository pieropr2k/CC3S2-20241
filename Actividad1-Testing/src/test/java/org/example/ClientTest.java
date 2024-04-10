package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ClientTest {
    private final Address address = new Address("street A");
    private final Address secondAddress = new Address("street B");

    private Client client;

    //Ejecute setUp antes de ejecutar cada metodo de prueba
    @BeforeEach
    void setUp(){
        client = new Client();
    }

    @Test
    void afterCreationShouldHaveNoAddress(){
        //client.addAddress(address);
    }

    @Test
    void shouldAllowToAddress(){
        client.addAddress(address);
    }

    @Test
    void shouldAllowToAddAddress () {
        client.addAddress(address);
        client.addAddress(secondAddress);
    }
}
