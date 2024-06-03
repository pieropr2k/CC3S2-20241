package org.example.model;
import java.util.Random;

public class NumerosAleatorios implements RandomNumbers {

    private int randomNumber;

    /*
    NumerosAleatorios(){
        Random random = new Random();
        this.randomNumber = random.nextInt(6) + 1; // Genera un número entre 1 y 6
    }
    */

    /*
    public int getRandomNumber() {
        return randomNumber;
    }
    */
    @Override
    int nextInt(int limit) {
        this.randomNumber = new Random().nextInt(limit) + 1; // Genera un número entre 1 y 6
        return this.randomNumber;
    }

}
