package org.example;

public class TaxCalculator {

    public double calculateTax(double value) {
        //La precondición: un simple if para garantizar que no pasen valores no válidos
        if(value < 0) {
            throw new RuntimeException("Value has to be positive");
        }

        double taxValue = 0;

        // some complex business rule here...
        // final value goes to 'taxValue'

        //La postcondición también se implementa como un simple if. Si algo sale mal, lanzamos una excepción,
        //alertando al consumidor que la postcondición no se cumple
        if(taxValue < 0) {
            throw new RuntimeException("Calculated tax cannot be negative");
        }

        return taxValue;
    }
}
