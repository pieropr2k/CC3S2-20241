package org.example;

import org.example.towers.Tower;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
