package org.example.Player;

public class PlayerService {
    private int score;
    private int baseHealth;
    public PlayerService() {
        this.score = 0;
        this.baseHealth = 100;
    }
    public int getScore() {
        return score;
    }
    public int getBaseHealth() {
        return baseHealth;
    }
}