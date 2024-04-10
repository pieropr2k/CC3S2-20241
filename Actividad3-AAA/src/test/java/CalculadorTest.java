import org.example.main.Calculador;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.*;

public class CalculadorTest {
    private Calculador calculador;
    int numeroA;
    int numeroB;

    @BeforeEach
    void setUp(){
        // Arrange
        calculador = new Calculador();
        numeroA = 10;
        numeroB = 5;
    }

    @Test
    public void testSum_PositiveNumbers_ShouldReturnCorrectSum() {
        // Arrange was in BeforeEach
        // Act
        int resultado = calculador.
                sumar(numeroA, numeroB);
        // Assert
        assertEquals(15, resultado, "10 + 5 deberia ser 15");
    }

    @Test
    public void testSubstraction_PositiveNumbers_ShouldReturnCorrectSum() {
        // Arrange was in BeforeEach
        // Act
        int resultado = calculador.restar(numeroA, numeroB);
        // Assert
        assertEquals(5, resultado, "10 - 5 deberia ser 5");
    }

    @Test
    public void testMult_PositiveNumbers_ShouldReturnCorrectSum() {
        // Arrange was in BeforeEach
        // Act
        int resultado = calculador.multiplicacion(numeroA, numeroB);
        // Assert
        assertEquals(50, resultado, "10 * 5 deberia ser 50");
    }

    @Test
    public void testDivision_PositiveNumbers_ShouldReturnCorrectSum() {
        // Arrange was in BeforeEach
        // Act
        double resultado = calculador.division(numeroA, numeroB);
        // Assert
        assertEquals(2, resultado, "10 / 5 deberia ser 2");
    }


    @Test
    public void testDivisionByZero_PositiveNumbers_ShouldReturnCorrectSum() {
        // Arrange
        numeroA = 10;
        numeroB = 0;

        // Act
        Exception exception = assertThrows(ArithmeticException.class, () -> calculador.division(numeroA, numeroB));

        // Assert
        assertNotNull(exception);
        assertEquals("Division por cero", exception.getMessage());
    }
}
