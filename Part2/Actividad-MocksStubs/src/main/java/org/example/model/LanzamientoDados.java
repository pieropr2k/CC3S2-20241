package org.example.model;

import java.util.random.RandomGenerator;

public class LanzamientoDados {
    private final int NUMERO_LADOS = 6;
    //private final RandomGenerator rnd;
    private final RandomNumbers rnd;
            //= RandomGenerator.getDefault();

    public LanzamientoDados(RandomNumbers r){
        this.rnd = r;
    }

    public String asText(){
        int lanzado = rnd.nextInt(NUMERO_LADOS);
        return String.format("El lanzamiento es "+ lanzado);
    }
}
