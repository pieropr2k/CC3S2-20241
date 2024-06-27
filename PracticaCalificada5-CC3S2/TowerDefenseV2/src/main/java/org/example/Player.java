package org.example;

import org.example.enemies.Enemy;

public class Player {
    private int score;
    private int baseHealth;
    public Player() {
        this.score = 0;
        this.baseHealth = 100;
    }
    public void addScore(int points) {
        this.score += points;
    }
    public void deductBaseHealth(int damage) {
        // Precondición: el valor debe ser no negativo
        assert damage >= 0 : "Damage down has to be positive";
        int baseHealthVal = this.baseHealth;
        baseHealthVal -= damage;
        // Postcondición: el valor del impuesto debe ser no negativo
        assert baseHealthVal >= 0 : "Life has to be positive";
        this.baseHealth -= baseHealthVal;
    }
    public int getScore() {
        return score;
    }
    public int getBaseHealth() {
        return baseHealth;
    }
    // Métodos adicionales según las necesidades del juego
    public void updateScoreAndHealth(Enemy enemy, boolean defeated){
        if (defeated) {
            this.addScore(enemy.getReward());
        } else {
            this.deductBaseHealth(25);
        }
    }
}