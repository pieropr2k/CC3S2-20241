package org.example;

import java.util.*;
import java.util.Scanner;
public class TowerDefenseGame {
    private Map map;
    private Player player;
    private List<Wave> waves;
    public TowerDefenseGame() {
        this.map = new Map();
        this.player = new Player();
        this.waves = new ArrayList<>();
    }
    public void placeTower(Tower tower, int x, int y) {
        map.placeTower(tower, x, y);
    }
    public void startWave() {
        Wave wave = new Wave();
        waves.add(wave);
        wave.start();
    }
    public void gameState() {
        System.out.println(map);
        System.out.println("Puntuación: " + player.getScore());
        System.out.println("Vida de la base: " + player.getBaseHealth());
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] numeros = new int[5];
        System.out.println("Por favor, introduce 5 números:");

        for (int i = 0; i < 5; i++) {
            System.out.print("Número " + (i + 1) + ": ");
            numeros[i] = scanner.nextInt();
        }
        System.out.println("Los números que has introducido son:");
        for (int numero : numeros) {
            System.out.println(numero);
        }
        //scanner.close();
    }
}