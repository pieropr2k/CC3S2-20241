import org.example.Game;
import org.example.HintGenerator;
import org.example.WordSelector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class HintGeneratorTest {
    private HintGenerator hintGenerator;
    @BeforeEach
    void setUp(){
        // Arrange
        this.hintGenerator = new HintGenerator(List.of("Tiene 2 'e'","Lo dices cuando juegas futbol", "Se les llama a las ofertas."));
    }
    @Test
    void getCorrectItem() {
        String hint = hintGenerator.getHint(0);
        assertThat(hint).isEqualTo("Tiene 2 'e'");
    }

    @Test
    void getIncorrectItem() {
        String hint = hintGenerator.getHint(1);
        assertThat(hint).isNotEqualTo("Tiene 2 'e'");
    }
}
