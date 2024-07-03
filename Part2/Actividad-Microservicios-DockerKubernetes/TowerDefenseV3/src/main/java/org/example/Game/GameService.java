package org.example.Game;

import org.example.Map.MapService;
import org.example.Player.PlayerService;
import org.example.Tower.TowerService;
import org.example.Wave.WaveService;

import java.util.ArrayList;
import java.util.List;

public class GameService {
    private MapService mapService;
    private PlayerService playerService;
    private List<WaveService> waves;
    public GameService() {
        this.mapService = new MapService();
        this.playerService = new PlayerService();
        this.waves = new ArrayList<>();
    }
    public void placeTower(TowerService tower, int x, int y) {
        mapService.placeTower(tower, x, y);
    }
    public void startWave() {
        WaveService wave = new WaveService();
        waves.add(wave);
        wave.start();
    }
    public void gameState() {
        System.out.println(mapService);
        System.out.println("Puntuación: " + playerService.getScore());
        System.out.println("Vida de la base: " + playerService.getBaseHealth());
    }
}