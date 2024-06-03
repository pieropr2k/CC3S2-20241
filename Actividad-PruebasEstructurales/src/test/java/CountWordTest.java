import org.example.CountWords;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CountWordTest {
    @Test
    void twoWordsEndingWithS() { // 1
        int words = new CountWords().count("dogs cats");
        assertThat(words).isEqualTo(2);
    }
    @Test
    void noWordsAtAll() { // 2
        int words = new CountWords().count("dog cat");
        assertThat(words).isEqualTo(0);
    }

    @Test
    void wordsThatEndInR() { // 1
        int words = new CountWords().count("car bar");
        assertThat(words).isEqualTo(2);
    }
}
