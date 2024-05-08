# Ritmo TDD

# Sprint 1: Configuración inicial y funcionalidad básica (2 puntos)

1. Configuración del proyecto: Crear un nuevo proyecto en Gradle y configurar dependencias para JUnit 5.
```gradle
plugins {
    id 'java'
}

group = 'org.example'
version = '1.0-SNAPSHOT'

repositories {
    mavenCentral()
}

dependencies {
    testImplementation platform('org.junit:junit-bom:5.9.1')
    testImplementation 'org.junit.jupiter:junit-jupiter'
    testImplementation 'org.assertj:assertj-core:3.23.1'
}

test {
    useJUnitPlatform()
}
```

2. Clase Word: Implementar la clase básica con el método guess que acepte una suposición y devuelva un resultado básico. Utiliza los fragmentos dados donde defines la clase Word y sus métodos.

Esto quiere decir que se retorna un resultado basico (en este caso INCORRECT), pero como se ve en el programa el **resultado** proviene de la clase Score:

```java
public class Word {
    private final String word;
    public Word(String correctWord) {
        this.word = correctWord;
    }
    public Score guess(String attempt) {
        var score = new Score(word);
        score.assess(attempt);
        return score;
    }
}
```
Aqui podemos visualizar:

```java
package com.wordz.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Score {
    private final String correct;
    private Letter resultado = Letter.INCORRECT ;
    public Score(String correct) {
        this.correct = correct;
    }
    public Letter letter(int position) {
        return resultado;
    }
    public void assess(int position, String attempt) {
        // completar codigo
    }
}
```

Ademas al vaciar el metodo assess de la clase Score, esto se hara con el fin de que se pueda hacer el testing aplicando TDD.



### TDD 1

3. Pruebas iniciales: Escribir pruebas para validar suposiciones incorrectas y correctas utilizando JUnit 5. Usa el código de ejemplo proporcionado en las pruebas oneIncorrectLetter y oneCorrectLetter.

Esta prueba unitaria verifica si la letra de la primera posicion (osea 0) que corresponde a la palabra correcta y la palabra intento coinciden.

```java
@Test
public void oneCorrectLetter() {
    var word = new Word("A");
    var score = word.guess("A");
    assertThat(score.letter(0)).isEqualTo(Letter.CORRECT);
}

@Test
public void oneIncorrectLetter() {
    var word = new Word("A");
    var score = word.guess("A");
    assertThat(score.letter(0)).isEqualTo(Letter.INCORRECT);
}
```



<span style="color:red;">1. RED</span>

Veamos como funciona la prueba: se esperaba CORRECT pero se obtuvo INCORRECT. Esto se debe a que como se menciono previamente siempre letter() devuelve INCORRECT por defecto (ya que no tiene ninguna logica implementada aun en assess()).

![outs](images/incorrectimputs.png)

<span style="color:green;">2. GREEN</span>

Ahora a mejorar el código de las clases que estan en main para que la prueba pase, en este caso el metodo assess() en Score ahora verificara si el caracter en la posición **position** de ambas cadenas es el mismo y si esto se cumple se almacena Letter.CORRECT en la variable de instancia resultado.

```java
public class Score {
    private final String correct;
    private Letter resultado = Letter.INCORRECT ;
    public Score(String correct) {
        this.correct = correct;
    }
    public Letter letter(int position) {
        return resultado;
    }
    
    public void assess(int position, String attempt) {
        if ( correct.charAt(position) == attempt.charAt(position)){
            resultado = Letter.CORRECT;
        }
    }
}
```

```java
public class Word {
    private final String word;
    public Word(String correctWord) {
        this.word = correctWord;
    }
    public Score guess(String attempt) {
        var score = new Score(word);
        score.assess( 0, attempt );
        return score;
    }
}
```


![outs](images/correctfunctions.png)

El método guess() de la clase Word llama al método assess() de Score que hace que se modifique el atributo *resultado* de la clase Word, justo este atributo que luego sera retornado por el metodo letter() para hacer la verificación en la prueba.

Se puede ver que las pruebas pasan tambien.



4. Refactorización y código limpio: Revisar el código para mejorar la claridad y la mantenibilidad.
Este item esta en la fase de Refactorizacion de cada ciclo TDD.

 <span style="color:blue;">3. REFACTOR</span>

El codigo esta mejorable.
Si bien se ejecutaron las pruebas y pasaron, hay que hacer que el codigo sea lo mas optimo posible. Por lo que ahora trataremos de controlar los code smells.

De esta parte del código de la clase Score podemos extraer la comparacion del metodo assess() e implementarla en un metodo isCorrectLetter() de tal forma que todo este entendible:

```java
public void assess(int position, String attempt) {
    if (isCorrectLetter(position, attempt)){
        resultado = Letter.CORRECT;
    }
}

private boolean isCorrectLetter(int position, String attempt) {
    return correct.charAt(position) == attempt.charAt(position);
}
```

De momento, del codigo de la fase RED, se puede ver en el metodo letter() que como parametro esta el metodo position sin embargo este parametro no influye en nada del codigo, ademas hay que considerar que de momentos solo se esta evaluando la posicion 0 de la palabra.
Asi que la siguiente refactorizacion seria convertir position en un atributo de la clase para que nos sea util cuando queramos adivinar la posicion 1 o 2 o la ultima de la palabra.

```java
public class Score {
    private final String correct;
    private Letter resultado = Letter.INCORRECT ;
    private int position;
    
    public Score(String correct) {
        this.correct = correct;
    }

    public Letter letter(int position) {
        return resultado;
    }

    public void assess(String attempt) {
        if (isCorrectLetter(attempt)){
            resultado = Letter.CORRECT;
        }
    }
    
    private boolean isCorrectLetter(String attempt) {
        return correct.charAt(position) == attempt.charAt(position);
    }
}
```

Una parte fundamental en cada paso de refactorizacion es verificar si las pruebas siguen corriendo despues de todo este proceso, si no se ha roto nada a la hora de refactorizar

Ya que las pruebas corrieron y ahora tenemos un codigo mas limpio por lo que podemos concluir que estamos en fase refactorizacion azul. 

![outs](images/testoutputs1.png)

# Sprint 2: Ampliación de la lógica de juego (2 puntos)

### TDD 2 - SPRINT 2

2. Pruebas de retroalimentación: Ampliar el conjunto de pruebas para cubrir todos los casos posibles de retroalimentación, incluyendo secondLetterWrongPosition y allScoreCombinations

De lo hecho en el TDD-1 se puede ver que el problema solo se ha contextualizado a palabras de una sola letra, por lo que ahora se hará casos para 2 letras.
Ahora vamos a introducir un nuevo método el cual indica que una letra puede estar presente en la palabra, pero no en la posición que adivinamos y se tiene que retornar PART_CORRECT.
Ahora pondremos esto mencionado de manera practica:
Esta prueba verifica que la letra de la posicion 1 de la palabra adivinar "ZA" sea parcialmente correcta (ya que se encuentra en la palabra word en la posicion 0 pero no la posicion 1)

```java
@Test
void secondLetterWrongPosition() {
    var word = new Word("AR");
    var score = word.guess("ZA");
    assertScoreForLetter(score, 1, Letter.PART_CORRECT);
}
```

<span style="color:red;">1. RED</span>
 
 Podemos ver que la prueba no pasó y es rojo:

![alt text](images/secondletteroutput.png)


<span style="color:green;">2. GREEN</span>

1. Ampliar clase Word: Implementar lógica para evaluar letras en posiciones correctas e incorrectas. Integra el código de assess y la evaluación de la posición de las letras.

Ahora toca corregir el codigo de main para que esta prueba pase

Este clase tiene el atributo position el cual sera un contador que va inicializado en 0 e ira incrementandose, como no hay nada que acabe con el loop esto devuelve el resultado de la ultima letra, de momentos funciona bien en nuestro codigo pero falta ver si sera apto para nuevos cambios y funcionalidades.


```java
public class Score {
    private final String correct;
    private Letter resultado = Letter.INCORRECT ;
    private int position;
    
    public Score(String correct) {
        this.correct = correct;
    }
    
    public Letter letter(int position) {
        return resultado;
    }
    
    public void assess(String attempt) {
        for (char current: attempt.toCharArray()) {
            if (isCorrectLetter(current)) {
                resultado = Letter.CORRECT;
            } else if (occursInWord(current)) {
                resultado = Letter.PART_CORRECT;
            }
            position++;
        }
    }
    
    private boolean isCorrectLetter(char currentLetter) {
        return correct.charAt(position) == currentLetter;
    }
    
    private boolean occursInWord(char current) {
        return correct.contains(String.valueOf(current));
    }
}
```
Podemos ver que las pruebas pasan:

![outs](images/testoutputs2.png)


### TDD 3

La prueba anterior estuvo bien, sin embargo tiene sus limitantes. Ya que si bien la prueba puede pasar pero es porque no estamos evaluando otras posibilidades, falta una posibilidad y es si la letra no cumple ninguna de las condiciones: osea no esta en la posicion correcto ni se encuentra en alguna posicion de la palabra a adivinar.

La siguiente prueba verifica 3 posibles resultados (adicionando INCORRECT), por lo que podemos considerar que podemos confiar mas en el codigo que pase esta prueba.

```java
    @Test
    void allScoreCombinations() {
        var word = new Word("ARI");
        var score = word.guess("ZAI");
        assertScoreForLetter(score, 0, Letter.INCORRECT);
        assertScoreForLetter(score, 1, Letter.PART_CORRECT);
        assertScoreForLetter(score, 2, Letter.CORRECT);
    }
```


 <span style="color:red;">1. RED</span>


Podemos ver que la prueba esta en rojo. Esto se debe a que no existe el caso INCORRECT aun.

![outbad](images/allscoreincorrect.png)

 <span style="color:green;">2. GREEN</span>

En este codigo lo que se hace es pasar los resultados para cada letra en un arreglo results

```java
public class Score {
    private final String correct;
    private final List<Letter> results = new ArrayList<>();
    private int position;
    
    public Score(String correct) {
        this.correct = correct;
    }
    
    public Letter letter(int position) {
        return results.get(position);
    }
    
    public void assess(String attempt) {
        for (char current: attempt.toCharArray()) {
            if (isCorrectLetter(current)) {
                results.add(Letter.CORRECT);
            } else if (occursInWord(current)) {
                results.add(Letter.PART_CORRECT);
            }  else {
                results.add(Letter.INCORRECT);
            }
            position++;
        }
    }
    
    private boolean isCorrectLetter(char currentLetter) {
        return correct.charAt(position) == currentLetter;
    }
}
```

Este codigo pasa nuestra prueba como se puede ver en la imagen:

![outs](images/testoutputs2.png)

3. Refactorización: Mejorar el diseño y la implementación basada en los resultados de las pruebas y el análisis de código estático

<span style="color:blue;">3. REFACTOR</span>

Estuvo muy bueno que las pruebas hayan pasado, ahora se refactorizara el codigo:

Extraemos la logica de asignar un resultado a cada letra en el método scoreFor(): 

```java
public void assess(String attempt) {
    for (char current: attempt.toCharArray()) {
        results.add( scoreFor(current) );
        position++;
    }
}
private Letter scoreFor(char current) {
    if (isCorrectLetter(current)) {
        return Letter.CORRECT;
    }
    if (occursInWord(current)) {
        return Letter.PART_CORRECT;
    }
    return Letter.INCORRECT;
}
```

En nuestro codigo de prueba en vez de repetir el metodo assertScoreFor() y cambiar de valores manualmente (este metodo es lo mismo que hacer un asset), ponemos esta logica en el metodo assertScoreForGuess() y automatizamos esto con un ciclo for:

```java
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
```

Una vez hecha la refactorizacion verificamos si las pruebas pasan:.

![outs](images/testoutputs2.png)


El codigo de las pruebas:

![](images/allfinalcode.png)


Preguntas relacionadas al juego Wordz (2 puntos)
1. ¿Cuáles son los elementos clave que se deben considerar al diseñar un juego de adivinanza de palabras como "Wordz"? ¿Cómo influyen los sistemas de retroalimentación en la
experiencia del jugador en juegos de trivia o adivinanzas?

Los resultados finales, y que se puedan adivinar varias palabras. Influye mucho porque asi el juego mejora su funcionalidad y si tiene errores con la experiencia del jugador sabremos donde corregir. 

2. Describe cómo el principio de "feedback continuo" en las metodologías ágiles podría aplicarse en el desarrollo del juego "Wordz".

Feedback continuo es un principio el cual da retroalimentacion al equipo de desarrolladores para que asi se tenga una conciencia de que se debe mejorar en la aplicacion, esto es ideal para tener un producto bueno y competitivo para el mercado.

3. ¿Cuáles son algunas características nuevas de JUnit 5 que lo hacen adecuado para pruebas unitarias en proyectos Java modernos? ¿Cómo se podría diseñar una suite de pruebas unitarias para validar la lógica de puntuación en "Wordz"?

Se tendria que almacenar la puntuacion del usuario iterativamente, ver en cada paso si corresponden los elementos y asi tambien ver si el puntaje final es el ideal.

4. ¿Por qué es importante la refactorización en el desarrollo continuo de un juego y cómo puede afectar la mantenibilidad del código? Proporciona un ejemplo de cómo podrías refactorizar un método complejo en el juego "Wordz" para mejorar la claridad y eficiencia.

Es importante ya que asi se mejora la eficiencia del software, ademas tambien se hace el codigo mas legible y mas entendible para los futuros desarrolladores que quieran contribuir y mantener el proyecto. Se refactorizo el codigo en las fases de REFACT.
