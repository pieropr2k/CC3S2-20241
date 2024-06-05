package org.example;

public class Calculadora {
    public static int sum(int a, int b) {
        return a+b;
    }

    public static int subst(int a, int b) {
        return a-b;
    }

    public static int mult(int a, int b) {
        return a*b;
    }

    public static int div(int a, int b) {
        if (b == 0) {
            throw new IllegalArgumentException("b no puede ser 0");
        }
        return a/b;
    }
}
