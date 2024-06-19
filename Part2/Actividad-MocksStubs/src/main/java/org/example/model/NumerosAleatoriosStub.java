package org.example.model;

public class NumerosAleatoriosStub implements NumerosAleatorios{
    int result;

    public void setNextInt(int newValue){
        this.result = newValue;
    }

    @Override
    public int nextInt(int upperBound) {
        return this.result;
    }
}