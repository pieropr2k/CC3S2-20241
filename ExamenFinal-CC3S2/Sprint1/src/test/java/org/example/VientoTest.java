package org.example;

import org.example.viento.Viento;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VientoTest {
    @Test
    public void isHigh(){
        Viento viento = new Viento(150);
        assertEquals(true, viento.isHigh());
    }

    @Test
    public void isNotHigh(){
        Viento viento = new Viento(600);
        assertEquals(false, viento.isHigh());
    }
}
