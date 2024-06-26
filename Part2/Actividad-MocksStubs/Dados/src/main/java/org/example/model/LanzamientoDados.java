package org.example.model;

public class LanzamientoDados {
    private final int NUMERO_LADOS = 6;
    private final NumerosAleatorios rnd;

    public LanzamientoDados(NumerosAleatorios r){
        this.rnd = r;
    }

    public int attempt(){
        int result = rnd.nextInt(NUMERO_LADOS) + 1;
        System.out.println("El lanzamiento es "+ result);
        return result;
    }
}
