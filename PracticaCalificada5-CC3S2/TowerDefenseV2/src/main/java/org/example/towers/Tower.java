package org.example.towers;

import org.example.Position;
import org.example.enemies.Enemy;

import java.util.List;

public class Tower {
    private int damage;
    private int range;
    private int fireRate; // turnos entre disparos
    private Position position;

    public Tower(int damage, int range, int fireRate) {
        this.damage = damage;
        this.range = range;
        this.fireRate = fireRate;
        this.position =  new Position(0, 0);
    }
    // Constructores, getters y setters
    public int getDamage() {
        return this.damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getRange() {
        return this.range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public int getFireRate() {
        return this.fireRate;
    }

    public void setFireRate(int fireRate) {

        this.fireRate = fireRate;
    }

    public void setPosition(int x, int y) {
        // Precondición: el valor debe ser no negativo
        assert (x >= 0 || y >= 0) : "Coordinates has to be positive";
        position.setPosition(x, y);
    }

    public void attack(List<Enemy> enemies) {
        // Precondicion
        if (enemies.isEmpty()) {
            throw new IllegalArgumentException("Tienes que tener minimo 1 enemigo");
        }

        for (Enemy enemy: enemies){
            int x_diff = Math.abs(enemy.getPosition().getX() - position.getX());
            int y_diff = Math.abs(enemy.getPosition().getY() - position.getY());
            if ((x_diff <= range || y_diff <= range) && (x_diff == y_diff || x_diff == 0 || y_diff == 0)) {
                int preHealth = enemy.getHealth();
                enemy.downHealth(damage);
                // Post-condition
                assert enemy.getHealth() == preHealth - damage : "It must downgrade life";

            }
        }
    }
}