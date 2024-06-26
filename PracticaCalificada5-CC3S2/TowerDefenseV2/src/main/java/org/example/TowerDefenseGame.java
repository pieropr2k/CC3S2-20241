package org.example;
import org.example.enemies.*;
import org.example.towers.Tower;

import java.util.List;

public class TowerDefenseGame {
    private Wave wave;
    private List<Enemy> enemies;
    private Map map;
    private Player player;

    public TowerDefenseGame(Map map, Wave wave){
        this.wave = wave;
        this.map = map;
        this.player = new Player();
    }

    public boolean isEnemyDied(Enemy enemy){
        return enemy.getHealth()<=0;
    }

    public void moveEnemies(){
        for (Enemy enemy: enemies){
            if (isEnemyDied(enemy)){
                enemies.remove(enemy);
                player.updateScoreAndHealth(enemy, true);
            }
            Position initialPos = enemy.getPosition();
            int positionIndex = getPositionIndex(initialPos.getX(), initialPos.getY());
            Position newPosition = map.getRoad().get(positionIndex+1);
            if (newPosition == map.getBase()){
                enemies.remove(enemy);
                player.updateScoreAndHealth(enemy, false);
            } else {
                map.placeEnemy(enemy, newPosition.getX(), newPosition.getY());
                map.placeRoad(initialPos.getX(), initialPos.getY());
            }
        }
    }

    public int getPositionIndex(int x, int y){
        int i=0;
        for (Position pos: map.getRoad()) {
            if (pos.getX() == x && pos.getY() == y) {
                break;
            }
            i++;
        }
        return i;
    }

    public void setWave(Wave wave) {
        this.wave = wave;
    }

    public void startWave() {
        enemies = wave.spawnEnemies();
    }

    public List<Enemy> getEnemies(){
        return this.enemies;
    }

    public void placeTower(Tower tower, int x, int y) {
        if (map.isValidPosition(x, y)){
            map.placeTower(tower, x, y);
        }
    }
}