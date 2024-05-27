import org.example.Calculadora;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CalculatorTest {
    @Test
    void testAdd() {
        assertThat(Calculadora.sum(4,5)).isEqualTo(9);
    }
}
