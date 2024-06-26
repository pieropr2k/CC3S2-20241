package org.example.towers;

public class DartTowerFactory implements TowerFactory{
    @Override
    public Tower createTower() {
        return new DartTower();
    }
}
