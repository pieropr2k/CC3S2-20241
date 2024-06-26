package org.example.model;
import java.util.Random;

public class NumerosAleatoriosGenerados implements NumerosAleatorios{
    private final Random rnd = new Random();
    @Override
    public int nextInt(int upperBound) {
        return rnd.nextInt(upperBound);
    }
}
