package org.example.enemies;

public class BossEnemyFactory implements EnemyFactory{
    @Override
    public Enemy createEnemy() {
        return new BossEnemy();
    }
}
