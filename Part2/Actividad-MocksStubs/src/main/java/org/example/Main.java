package org.example;

import org.example.model.LanzamientoDados;
import org.example.model.NumerosAleatorios;
import org.example.model.NumerosAleatoriosGenerados;

public class Main {
    public static void main(String[] args) {
        NumerosAleatorios numerosAleatorios = new NumerosAleatoriosGenerados();
        LanzamientoDados lanzamientoDados = new LanzamientoDados(numerosAleatorios);
        System.out.println("Primer Lanzamiento:");
        System.out.println(lanzamientoDados.attempt());
        System.out.println("Segundo Lanzamiento:");
        System.out.println(lanzamientoDados.attempt());
        System.out.println("Tercer Lanzamiento:");
        System.out.println(lanzamientoDados.attempt());
        System.out.println("Cuarto Lanzamiento:");
        System.out.println(lanzamientoDados.attempt());
    }
}