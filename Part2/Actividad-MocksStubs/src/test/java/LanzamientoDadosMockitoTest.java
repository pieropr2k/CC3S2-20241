import org.example.model.LanzamientoDados;
import org.example.model.NumerosAleatoriosStub;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LanzamientoDadosMockitoTest {
    // usaremos mockito
    @Mock
    private NumerosAleatoriosStub stub;

    @InjectMocks
    private LanzamientoDados play;

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4, 5})
    void multipleAttemptsWithMockito(int resultadoEsperado) {
        // Arrange
        // hace que *cuando* se invoque a linea con stub.nextInt(6) se retorne *resultadoEsperado*, por esto usamos *when*
        when(stub.nextInt(6)).thenReturn(resultadoEsperado);
        //Act
        // dentro del metodo attempt() se llamara a la linea de arriba, asi que el when hará su trabajo
        int resultado = play.attempt();
        //Assert
        assertThat(resultado).isEqualTo(resultadoEsperado + 1);
    }
}