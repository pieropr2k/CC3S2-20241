package org.example.Map;
import org.example.Tower.TowerService;

public class MapService {
    private char[][] grid;
    public MapService() {
        grid = new char[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                grid[i][j] = ' ';
            }
        }
    }
    public void placeTower(TowerService tower, int x, int y) {
        grid[x][y] = tower.getSymbol();
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (char[] row : grid) {
            for (char cell : row) {
                sb.append("[").append(cell).append("]");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}