package org.example;

import org.example.enemies.Enemy;
import org.example.enemies.SpeedyEnemy;
import org.example.towers.Tower;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class TowerContractTest {
    @Test
    public void testTowerPreCondition() {
        Tower tower = new Tower(30, 20, 2);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            tower.attack(new ArrayList<>());
        });
        assertEquals(exception.getMessage(), "Tienes que tener minimo 1 enemigo");
    }
}
