import org.example.model.LanzamientoDados;
import org.example.model.NumerosAleatoriosStub;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class LanzamientoDadosTest {
    @Test
    void oneAttempt(){
        // Arrange
        NumerosAleatoriosStub stub = new NumerosAleatoriosStub();
        int nextIntBound = 3;
        stub.setNextInt(nextIntBound);
        LanzamientoDados play = new LanzamientoDados(stub);
        // Act
        int attempt = play.attempt();
        // Assert
        assertThat(attempt).isEqualTo(nextIntBound+1);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4, 5})
    void multipleAttempts(int resultadoEsperado) {
        // Arrange
        NumerosAleatoriosStub stub = new NumerosAleatoriosStub();
        stub.setNextInt(resultadoEsperado);
        LanzamientoDados play = new LanzamientoDados(stub);
        // Act
        int resultado = play.attempt();
        // Assert
        assertThat(resultado).isEqualTo(resultadoEsperado + 1);
    }
}