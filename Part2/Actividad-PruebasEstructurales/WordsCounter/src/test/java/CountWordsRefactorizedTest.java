import org.example.CountWordsRefactorized;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;

public class CountWordsRefactorizedTest {
    @Test
    void twoWordsEndingWithS() { // 1
        String str = "dogs cats";
        assertWordsThatEndsWithR_or_S(str,2);
    }
    @Test
    void noWordsAtAll() { // 2
        String str = "dog cat";
        assertWordsThatEndsWithR_or_S(str,0);
    }

    @Test
    void wordsThatEndInR() { // 1
        String str = "car bar";
        assertWordsThatEndsWithR_or_S(str,2);
    }

    private void assertWordsThatEndsWithR_or_S(String str, int expectedValue){
        CountWordsRefactorized countWords = new CountWordsRefactorized(str);
        int words = countWords.count();
        assertThat(words).isEqualTo(expectedValue);
    }

    @ParameterizedTest
    @ValueSource(strings = { "", "holas.", "haciendo la prueba ", "haciendo la prueba pues."})
    void constructorShouldThrowExceptionForIllegalGamesNb(String illegalStr) {
        assertThatExceptionOfType(IllegalArgumentException.class)
        .isThrownBy(() -> {
            new CountWordsRefactorized(illegalStr);
        });
    }
}
