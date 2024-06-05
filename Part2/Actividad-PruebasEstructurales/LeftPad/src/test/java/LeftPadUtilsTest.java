import static org.assertj.core.api.Assertions.assertThat;

import org.example.LeftPadUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;
public class LeftPadUtilsTest {
    @ParameterizedTest
    @MethodSource("generator")

    // Hace una prueba unitaria en base a los parametros del metodo leftPad de la clase LeftPadUtils
    void test(String originalStr, int size, String padString, String expectedStr) { // 1
        assertThat(LeftPadUtils.leftPad(originalStr, size, padString)).isEqualTo(expectedStr);
    }
    // Se hace la prueba generator() con una lista de parametros
    static Stream<Arguments> generator() { // 2
        return Stream.of(
                Arguments.of(null, 10, "-", null), // T1
                Arguments.of("", 5, "-", "-----"), // T2
                Arguments.of("abc", -1, "-", "abc"), // T3
                // Correccion en T4 y T5
                Arguments.of("abc", 5, null, "  abc"), // T4
                Arguments.of("abc", 5, "", "  abc"), // T5
                Arguments.of("abc", 5, "-", "--abc"), // T6
                Arguments.of("abc", 3, "-", "abc"), // T7
                Arguments.of("abc", 0, "-", "abc"), // T8
                Arguments.of("abc", 2, "-", "abc"), // T9

                // ... otros casos de prueba aquí
                Arguments.of("abc", 5, "--", "--abc"), // T10
                Arguments.of("abc", 5, "---", "--abc"), // T11
                Arguments.of("abc", 5, "-", "--abc") // T12

        );
    }

    @Test
    void sameInstance() {
        String str = "sometext";
        assertThat(LeftPadUtils.leftPad(str, 5, "-")).isSameAs(str);
    }

}