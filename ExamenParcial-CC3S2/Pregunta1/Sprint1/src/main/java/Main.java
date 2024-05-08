import org.example.Question;
import org.example.Quiz;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        final List<Question> questions = List.of(
                new Question(" ¿Cuál es la capital de Francia?",
                        List.of("Madrid", "Londres", "Paris", "Berlin"),
                        "Paris"),
                new Question(" ¿Cuál es la capital de Peru?",
                        List.of("Madrid", "Namekusei", "Lima", "Bogota"),
                        "Lima"),
                new Question(" ¿Quien mato a Nappa?",
                        List.of("Freezer", "Goku", "Luffy", "Vegeta"),
                        "Vegeta"),
                new Question(" ¿Quien gano el mundial del 2022?",
                        List.of("Croacia", "Londres", "Francia", "Argentina"),
                        "Argentina"),
                new Question("¿Cual fue el ultimo campeon de la UCL?",
                        List.of("PSG", "Manchester City", "Real Madrid", "PSG"),
                "Manchester City"),
                new Question(" ¿Que lenguaje es el mas antiguo?",
                        List.of("Go", "Assembler", "Perl", "Sonarqube"),
                        "Assembler"),
                new Question(" ¿Cuál es la capital de Chile?",
                        List.of("Madrid", "Londres", "Paris", "Santiago"),
                        "Santiago"),
                new Question(" ¿Cuál es un pais de America?",
                        List.of("España", "Brasil", "Paris", "Alemania"),
                        "Brasil"),
                new Question(" ¿Cuál es un pais de Asia?",
                        List.of("Islandia", "Portugal", "Afganistan", "Berlin"),
                        "Afganistan"),
                new Question("¿Cual es una carrera de FC?",
                        List.of("Arquitectura", "Ing Civil", "Quimica", "Economia"),
                        "Quimica"),
        );
        Quiz quiz = new Quiz(questions);
        quiz.loadQuestions();
        quiz.loadFinalResult();
    }
}