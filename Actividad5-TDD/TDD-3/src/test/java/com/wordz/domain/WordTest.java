package com.wordz.domain;

import org.junit.jupiter.api.Test;

import static com.wordz.domain.Letter.*;
import static org.assertj.core.api.Assertions.assertThat;

public class WordTest {
    @Test
    public void oneIncorrectLetter() {
        var word = new Word("A");
        var score = word.guess("A");
        // Una vez más, en lugar de agregar un comentario al código fuente, hemos usado un nombre de método para capturar la intención del código assertThat().
        assertScoreForLetter(score, 0, Letter.CORRECT);
        //assertThat(score.letter(0)).isEqualTo(CORRECT);
        // score lo que hace es hacer que se adivine la palabra
        // score.letter() lo que devuelve es un numero que indica el resultado del intento de adivinanza
        // y 0 es cuando el numero es correcto
    }
    // Completa...
    @Test
    void secondLetterWrongPosition() {
        var word = new Word("AR");
        var score = word.guess("ZA");
        assertScoreForLetter(score, 1, Letter.PART_CORRECT);
    }

    private void assertScoreForLetter(Score score, int position, Letter expected) {
        assertThat(score.letter(position)).isEqualTo(expected);
    }

    @Test
    void allScoreCombinations() {
        var word = new Word("ARI");
        var score = word.guess("ZAI");
        assertScoreForGuess(score, INCORRECT, PART_CORRECT, CORRECT);
    }

    private void assertScoreForGuess(Score score, Letter... expectedScores){
        for (int position=0; position < expectedScores.length; position++){
            Letter expected = expectedScores[position];
            assertThat(score.letter(position)).isEqualTo(expected);
        }
    }
}