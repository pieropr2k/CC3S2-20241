package org.example;

import org.example.lluvia.Lluvia;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LluviaTest {
    @Test
    public void isIntense(){
        Lluvia lluvia = new Lluvia(0.8);
        assertEquals(true, lluvia.isIntense());
    }

    @Test
    public void isNotIntense(){
        Lluvia lluvia = new Lluvia(0.2);
        assertEquals(false, lluvia.isIntense());
    }
}
