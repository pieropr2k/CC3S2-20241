package org.example.enemies;

import org.example.Position;

public class Enemy {
    private int speed; // número de celdas por turno
    private int health;
    private int reward;
    private Position position;

    public Enemy(int speed, int health, int reward) {
        this.speed = speed;
        this.health = health;
        this.reward = reward;
        this.position = new Position(0, 0);
    }
    // Constructores, getters y setters
    public int getSpeed(){
        return this.speed;
    }
    public void setSpeed(int speed){
        this.speed = speed;
    }
    public int getHealth(){
        return this.health;
    }
    public void downHealth(int damage){
        // Pre-condition
        assert damage >= 0 : "Damage down has to be positive";
        int healthVal = this.health;
        healthVal -= damage;
        // Post-condition
        assert healthVal >= 0 : "Health has to be positive";
        this.health = healthVal;
    }
    public int getReward(){
        return this.reward;
    }

    public void setReward(int extra){
        // Pre-condition
        assert extra >= 0 : "Extra reward has to be positive";
        this.reward = this.reward + extra;
    }
    public void setPosition(int x, int y) {
        position.setPosition(x, y);
    }
    public Position getPosition() {
        return this.position;
    }
}