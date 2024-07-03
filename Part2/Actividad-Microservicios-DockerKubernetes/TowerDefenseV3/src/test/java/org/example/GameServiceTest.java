package org.example;

import org.example.Game.GameService;
import org.example.Map.MapService;
import org.example.Player.PlayerService;
import org.example.Tower.TowerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
public class GameServiceTest {
    @Mock
    private MapService mockMapService;
    @Mock
    private PlayerService mockPlayerService;
    @InjectMocks
    private GameService gameService;
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void testPlaceTower() {
        TowerService mockTower = mock(TowerService.class);
        gameService.placeTower(mockTower, 2, 2);
        verify(mockMapService).placeTower(mockTower, 2, 2);
    }
}