package org.example;

import org.example.model.Wave;
import org.example.model.enemies.Enemy;

import java.util.List;

public class Game {
    private Wave wave;
    private List<Enemy> enemies;

    /*
    public Game(Wave wave){
        this.wave = wave;
    }
    */

    public static void main(String[] args) {
        System.out.println("Hello world!");
    }

    public void setWave(Wave wave) {
        this.wave = wave;
    }

    public List<Enemy> getEnemies(){
        return this.wave.getEnemies();
    }

    public void startWave() {
        List<Enemy> newwave = wave.spawnEnemies();
        System.out.println(newwave);
        wave.setEnemies(newwave);
    }
}