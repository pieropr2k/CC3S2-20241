import org.example.Game;
import org.example.HintGenerator;
import org.example.WordSelector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;

import java.util.List;

public class GameTest {
    private Game game;

    @BeforeEach
    void setUp(){
        // Arrange
        HintGenerator hintGenerator = new HintGenerator(List.of("Tiene 2 'e'","Lo dices cuando juegas futbol", "Se les llama a las ofertas."));
        WordSelector wordSelector = new WordSelector("remate");
        this.game = new Game(4, wordSelector, hintGenerator);
    }

    @Test
    void badTryTesting() {
        game.tryEvent("hola");
        assertThat(game.getYouWin()).isEqualTo(false);
    }

    @Test
    void goodTryTesting() {
        game.tryEvent("remate");
        assertThat(game.getYouWin()).isEqualTo(true);
    }

    @Test
    void numberOfTriesTesting() {
        game.tryEvent("hola");
        assertThat(game.getNumberOfTrys()).isEqualTo(1);
    }

    @Test
    void goodMultipleTriesTesting() {
        game.tryEvent("lol");
        game.tryEvent("remate");
        assertThat(game.getYouWin()).isEqualTo(true);
    }
}
