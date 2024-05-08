package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Quiz {

    List<Question> questions = new ArrayList<>();
    private int correctAnswers;
    private int incorrectAnswers;

    public Quiz(List<Question> questions){
        this.questions = questions;
        this.correctAnswers = 0;
        this.incorrectAnswers = 0;
    }

    public void loadQuestions () {
        for (int i = 0; i < this.questions.size(); i++) {
            Question question = this.questions.get(i);
            System.out.println("Pregunta "+ (i+1) + ": " + question.getQuestion());
            List<String> options = question.getOptions();

            for (int j = 0; j < options.size(); j++) {
                System.out.println((j+1) + ") " + options.get(j));
            }
            System.out.print("Tu respuesta: ");
            Scanner sc = new Scanner(System.in);
            //String xd = sc.toString();
            if (question.isCorrectAnswer(sc.nextInt())) {
                System.out.println("¡Correcto!");
                this.correctAnswers++;
            } else {
                System.out.println("¡Incorrecto!");
                this.incorrectAnswers++;
            }
        }
    }

    public void loadFinalResult() {
        System.out.println("Juego terminado. Aquí está tu puntuación:");
        System.out.println("Preguntas contestadas: " + this.questions.size());
        System.out.println("Preguntas correctas: " + this.correctAnswers);
        System.out.println("Preguntas incorrectas: " + this.incorrectAnswers);
    }

    public int getCorrectAnswers() {
        return correctAnswers;
    }

    public void setCorrectAnswers(int correctAnswers) {
        this.correctAnswers = correctAnswers;
    }
}
