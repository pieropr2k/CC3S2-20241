import org.example.main.Calculador;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
    public void testSumPositiveNumbers_ShouldReturnCorrectSum() {
        // Arrange was in BeforeEach
        // Act
        int resultado = calculador.
                sumar(numeroA, numeroB);
        // Assert
        assertEquals(15, resultado, "10 + 5 deberia ser 15");
    }

    @Test
    public void testSumNegativeNumbers_ShouldReturnCorrectSum() {
        // Arrange
        numeroA = -10;
        numeroB = -5;
        // Act
        int resultado = calculador.
                sumar(numeroA, numeroB);
        // Assert
        assertEquals(-15, resultado, "(-10) + (-5) deberia ser -15");
    }

    @Test
    public void testSumNegativeWithPositive_ShouldReturnCorrectSum() {
        // Arrange
        numeroA = -10;
        // Act
        int resultado = calculador.
                sumar(numeroA, numeroB);
        // Assert
        assertEquals(-5, resultado, "(-10) + (5) deberia ser -5");
    }

    @Test
    public void testSumPositiveWithNegative_ShouldReturnCorrectSum() {
        // Arrange
        numeroB = -5;
        // Act
        int resultado = calculador.
                sumar(numeroA, numeroB);
        // Assert
        assertEquals(5, resultado, "10 + (-5) deberia ser 5");
    }

    @Test
    public void testSubtractionPositiveNumbers_ShouldReturnCorrectSubtr() {
        // Arrange was in BeforeEach
        // Act
        int resultado = calculador.restar(numeroA, numeroB);
        // Assert
        assertEquals(5, resultado, "10 - 5 deberia ser 5");
    }

    @Test
    public void testSubtractionNegativeWithPositive_ShouldReturnCorrectSubtr() {
        // Arrange
        numeroA = -10;
        // Act
        int resultado = calculador.restar(numeroA, numeroB);
        // Assert
        assertEquals(-15, resultado, "-10 - 5 deberia ser -15");
    }

    @Test
    public void testSubtractionPositiveWithNegative_ShouldReturnCorrectSubtr() {
        // Arrange
        numeroB = -5;
        // Act
        int resultado = calculador.restar(numeroA, numeroB);
        // Assert
        assertEquals(15, resultado, "10 - (-5) deberia ser 15");
    }

    @Test
    public void testMultiplyPositiveNumbers_ShouldReturnCorrectMult() {
        // Arrange was in BeforeEach
        // Act
        int resultado = calculador.multiplicacion(numeroA, numeroB);
        // Assert
        assertEquals(50, resultado, "10 * 5 deberia ser 50");
    }

    @Test
    public void testMultiplyNumberWithZero_ShouldReturnCorrectMult() {
        // Arrange
        numeroB = 0;
        // Act
        int resultado = calculador.multiplicacion(numeroA, numeroB);
        // Assert
        assertEquals(0, resultado, "10 * 0 deberia ser 0");
    }

    @Test
    public void testDivisionPositiveNumbers_ShouldReturnCorrectDiv() {
        // Arrange was in BeforeEach
        // Act
        double resultado = calculador.division(numeroA, numeroB);
        // Assert
        assertEquals(2, resultado, "10 / 5 deberia ser 2");
    }

    @Test
    public void testDivisionNegativeNumbers_ShouldReturnCorrectDiv() {
        // Arrange
        numeroA = -10;
        numeroB = -5;
        // Act
        double resultado = calculador.division(numeroA, numeroB);
        // Assert
        assertEquals(2, resultado, "-10 / -5 deberia ser 2");
    }

    @Test
    public void testDivisionPositiveWithNegative_ShouldReturnCorrectDiv() {
        // Arrange
        numeroB = -5;
        // Act
        double resultado = calculador.division(numeroA, numeroB);
        // Assert
        assertEquals(-2, resultado, "10 / -5 deberia ser -2");
    }

    @Test
    public void testDivisionNegativeWithPositive_ShouldReturnCorrectDiv() {
        // Arrange
        numeroA = -10;
        // Act
        double resultado = calculador.division(numeroA, numeroB);
        // Assert
        assertEquals(-2, resultado, "-10 / 5 deberia ser -2");
    }

    @Test
    public void testDivisionNumberByZero_ShouldThrowException() {
        // Arrange
        numeroA = -10;
        numeroB = 0;

        // Act
        Exception exception = assertThrows(ArithmeticException.class, () -> calculador.division(numeroA, numeroB));

        // Assert
        assertNotNull(exception);
        assertEquals("Division por cero", exception.getMessage());
    }

    @Test
    public void testDivisionZeroByZero_ShouldThrowException() {
        // Arrange
        numeroA = 0;
        numeroB = 0;

        // Act
        Exception exception = assertThrows(ArithmeticException.class, () -> calculador.division(numeroA, numeroB));

        // Assert
        assertNotNull(exception);
        assertEquals("Division por cero", exception.getMessage());
    }
}
