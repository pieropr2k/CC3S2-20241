package org.example.towers;

public class SniperTowerFactory implements TowerFactory{
    @Override
    public Tower createTower() {
        return new SniperTower();
    }
}
