# Actividad: Pruebas Estructurales

```java
public class CountWords {
    public int count(String str) {
        int words = 0;
        char last = ' ';
        for (int i = 0; i < str.length(); i++) { // 1
            if (!isLetter(str.charAt(i)) && (last == 's' || last == 'r')) { // 2
                words++;
            }
            last = str.charAt(i); // 3
        }
        if (last == 'r' || last == 's') {
            words++;
        }
        return words;
    }
    private boolean isLetter(char c) {
        return Character.isLetter(c);
    }
}
```

### Ejercicio 1
El proposito del programa es contar solo las palabras del string que terminan en s o r.

**Linea 1:** Se itera en funcion del tamaño del string *str*.

**Linea 2:**
Es una condicional la cual dependiendo de la veracidad se ejecuta la suma de *words* o no.
Se verifica si el caracter actual es un espacio vacio si no lo es simplemente seguimos avanzando, pero si lo es se da la veracidad si el caracter anterior (last) es "s" o "r".

**Linea 3:**
La variable *last* indica cual es el caracter anterior. Se almacena el caracter actual como el futuro caracter anterior mediante esta variable.




### Ejercicio 2

Explica qué hacen las líneas 1 y 2 del código. . Presenta un informe generado por JaCoCo
(www.jacoco.org/jacoco) u otra herramienta de cobertura de código de tu preferencia en el IDE del
curso.

```java
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

}
```
### Solución:

**Linea 1:**
Es una prueba unitaria donde como parametro tenemos 2 palabras que terminan en "s", se espera un retorno de 2 y esto indica que **siempre** se ejecuta lo que hay dentro de la condicional en este caso.

**Linea 2:**
Es una prueba unitaria donde como parametro tenemos 2 palabras que NO terminan en "s", se espera un retorno de 2 y esto indica que **nunca** se ejecuta lo que hay dentro de la condicional en este caso.

**Analisis de cobertura con Jacoco:** <br/>


![](img/ex2_jacoco_methods.png)

![](img/ex2_jacoco_code.png)


*Verde:* Se ejecutó al menos una vez durante la ejecución de las pruebas. <br/>
*Amarillo:* Se ejecuto parcialmente. <br/>
*Rojo:* No se ejecuto ni una vez en las pruebas. <br/>

Jacoco indica en el metodo count() un coverage del 83% esto es debido a que no se ha analizado el caso de las palabras que terminan en "r" por lo que hay una incertidumbre grande si estas podrian pasar las pruebas o no.

### Ejercicio 3

Explica la línea 1 y con el caso de prueba recién agregado en el conjunto de pruebas,
vuelve a ejecutar la herramienta de cobertura. Explica los cambios obtenidos.

```java
@Test
void wordsThatEndInR() { // 1
    int words = new CountWords().count("car bar");
    assertThat(words).isEqualTo(2);
}
```
### Solución:

**Linea 1:**
Es una prueba unitaria donde como parametro tenemos 2 palabras que terminan en "r", se espera un retorno de 2 y esto indica que **siempre** se ejecuta lo que hay dentro de la condicional en este caso.

Añadimos este unit test a la clase CountWordTest. <br/>

**Analisis de cobertura con Jacoco:** <br/>

![](img/ex3_jacoco_methods.png)

![](img/ex3_jacoco_code.png)

Jacoco indica en el metodo count() un coverage del 100% esto es debido a que una vez analizado el caso de las palabras que terminan en "r" ya tenemos todos los casos posibles a testear.


### Ejercicio 4

### Parte A:
```java
public class CountWords {
    public int count(String str) {
        int words = 0;
        char last = ' ';
        for (int i = 0; i < str.length(); i++) { // 1
            if (!isLetter(str.charAt(i)) && (last == 's' || last == 'r')) { // 2
                words++;
            }
            last = str.charAt(i); // 3
        }
        if (last == 'r' || last == 's') {
            words++;
        }
        return words;
    }
    private boolean isLetter(char c) {
        return Character.isLetter(c);
    }
}
```
### Solución:
1. Explica qué hacen las líneas 1, 2 y 3 en el código. <br/>
Explicado en el Ejercicio 1. <br/>

2. ¿Qué sucedería si se eliminara la línea 3 del código? <br/>
La variable *last* seria siempre ' ', esto quiere decir que la condicional de la linea 2 seria siempre falsa y la otra tambien, por lo que words seria 0.
Inclusive el editor lo indica.

![](img/non_linea3.png)

3. Escribe una descripción de alto nivel de lo que hace este método count. <br/>
El proposito del programa es contar solo las palabras del string que terminan en s o r.


### Parte B:
```java
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
}
```
### Solución:
4. Explica qué hacen las líneas 1 y 2 del código de prueba.<br/>
Explicado en el Ejercicio 2. <br/>

5. ¿Qué tipos de casos de prueba adicionales se deberían agregar para mejorar la cobertura?<br/>
Donde el caso sean palabras que acaben en "r", una acabe en "r" y la otra no, una acabe en "s" y la otra no, una acabe en "r" y la otra en "s". <br/>


### Parte C: Ejecución de Herramienta de Cobertura
6. Ejecuta las pruebas unitarias usando JaCoCo o una herramienta de cobertura de código de tu elección.<br/>
Explicado en el Ejercicio 2 en la seccion "Analisis de cobertura con Jacoco", esta en imagen. <br/>

7. Genera y presenta el informe de cobertura.<br/>
Explicado en el Ejercicio 2 en la seccion "Analisis de cobertura con Jacoco", esta en imagen. <br/>

8. Identifica las partes del código que no están cubiertas por las pruebas.<br/>
De la imagen se ve que la linea 8 y la linea 13 no estan cubiertas y estan indicadas de color amarillo, esto debido a que al no existir ninguna prueba unitaria que testee el caso donde una palabra termina en "r" hay una gran incertidumbre y no se sabe si se podria pasar la prueba unitaria o no en este caso.<br/>



### Ejercicio 5: Extensión de pruebas
### Parte A: Nuevas pruebas
Escribe nuevas pruebas unitarias para cubrir los casos que no están cubiertos actualmente. Usa el siguiente formato como guía.
```java
@Test
void wordsThatEndInR() { // 1
    int words = new CountWords().count("car bar");
    assertThat(words).isEqualTo(2);
}
```

### Parte B: Ejecución de herramienta de cobertura
1. Agrega las nuevas pruebas al conjunto de pruebas.
```java
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
```
2. Vuelve a ejecutar la herramienta de cobertura.
3. Explica los cambios obtenidos en el informe de cobertura. <br/>
2 y 3 realizados en el Ejercicio 3 en la seccion "Analisis de cobertura con Jacoco".

4. Si todavía hay partes del código no cubiertas, repite el proceso: identifica las partes no cubiertas, comprende por qué no están cubiertas y escribe una prueba que ejerza esa parte del código. <br/>
Como se puede ver en las imagenes el Coverage se indica que es de 100% por lo que no hay partes no cubiertas en nuestro codigo y todo el codigo esta bajo prueba.



### Ejercicio 6: Exploración y mejora
### Parte A: Exploración
1. Modifica el método count para mejorar su claridad o eficiencia.

```java
public class CountWordsRefactorized {
    private final String str;

    public CountWordsRefactorized(String str){
        if (str.isEmpty() || stringDoesntEndsWithLetter(str)) {
            throw new IllegalArgumentException("Length is 0");
        }
        this.str = str + " ";
    }

    public int count() {
        int words = 0;
        // Comenzamos desde 1 nuestro analisis
        for (int i = 1; i < str.length(); i++) { // 1
            if (lastCharacterSatisfyTheCondition(str.charAt(i), str.charAt(i-1))) { // 2
                words++;
            }
        }
        return words;
    }

    private boolean stringDoesntEndsWithLetter(String str){
        return !isLetter(str.charAt(str.length()-1));
    }

    private boolean lastCharacterSatisfyTheCondition(Character currentChar, Character lastChar) {
        return !isLetter(currentChar) && (lastChar == 's' || lastChar == 'r');
    }

    private boolean isLetter(char c) {
        return Character.isLetter(c);
    }
}

```

2. Escribe pruebas unitarias adicionales para asegurar que las modificaciones no rompan la funcionalidad existente.

```java
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
    @ValueSource(strings = { "", "holas.", "asi se hacee ", "asi se hace pues."})
    void constructorShouldThrowExceptionForIllegalGamesNb(String illegalStr) {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> {
                    new CountWordsRefactorized(illegalStr);
                });
    }
}
```

### Parte B: Informe final
3. Genera un informe final de cobertura después de todas las modificaciones y nuevas pruebas.

![](img/ex6_jacoco_methods.png)
![](img/ex6_jacoco_code.png)

4. Compara el informe final con el informe inicial y discute los cambios y mejoras en la cobertura de código.

![](img/ex2_jacoco_methods.png)

Haciendo la comparativa, se puede ver que el coverage tanto en el informe inicial y en el informe final son los mismos (y de 100%). 
Sin embargo el codigo del informe final es mas optimo debido a que se consideran mas casos como por ejemplo si el string es vacio o si el string termina en cualquier cosa menos en un caracter (numero, punto, espacio en blanco, etc) y gracias a la refactorizacion ponemos un filtro para que estos casos no se pasen.


### Ejercicio 7: presenta un gráfico de flujo de control (CFG) del programa CountWords







### Ejercicio 8: Cobertura de línea

### Ejercicio 9: Cobertura de ramas





### Ejercicio 10: Condición + cobertura de rama
Asegurar que cada condición y cada rama del código en el método count esté cubierta por al menos una prueba unitaria.


### Ejercicio 11: Cobertura de rutas
Asegurar que todas las rutas posibles de ejecución en el método count estén cubiertas por al menos una prueba unitaria.

### Ejercicio 12: Explica los comentarios 1, 2, 3, 4 y 5 del código
```java
public class LeftPadUtils {
    private static final String SPACE = " ";
    private static boolean isEmpty(final CharSequence cs) {
        return cs == null || cs.length() == 0;
    }
    /**
     * @param str
     * @param size
     * @param padStr
     * @return left-padded string or {@code null}
     */
    public static String leftPad(final String str, final int size, String padStr) {
        if (str == null) { // 1
            return null;
        }
        if (isEmpty(padStr)) { // 2
            padStr = SPACE;
        }
        final int padLen = padStr.length();
        final int strLen = str.length();
        final int pads = size - strLen;
        if (pads <= 0) { // 3 
            return str;
        }
        if (pads == padLen) { // 4
            return padStr.concat(str);
        } else if (pads < padLen) { // 5
            return padStr.substring(0, pads).concat(str);
        } else { // 6
            final char[] padding = new char[pads];
            final char[] padChars = padStr.toCharArray();
            for (int i = 0; i < pads; i++) {
                padding[i] = padChars[i % padLen];
            }
            return new String(padding).concat(str);
        }
    }
}
```

El proposito del programa es contar solo las palabras del string que terminan en s o r.
**Linea 1:** Verifica si el string str es nulo, si lo es se retorna null.
<br/>
**Linea 2:**
Verifica si el string *padStr* es vacio, si lo es *padStr* sera " ".
<br/>
**Linea 3:**
Si *size* es menor o igual que el tamaño de *str* entonces se returna *str*
<br/>
**Linea 4:**
Si *pads* es igual al tamaño de *padStr* entonces se retorna la union de padStr con *str*
<br/>
**Linea 5:**
Si *pads* es menor al tamaño de *padStr* entonces se retorna la union del substring de padStr con *str*.
<br/>
**Linea 6:**
Si pads es mayor a padLen (pads > padLen) entonces se retorna el string *padding* el cual contiene letras de *padStr* se unido con *str*.

### Ejercicio 13: Explica las líneas 1 y 2.
```java
public class LeftPadTest {
    @ParameterizedTest
    @MethodSource("generator")
    void test(String originalStr, int size, String padString, String expectedStr) { // 1
        assertThat(LeftPadUtils.leftPad(originalStr, size, padString)).isEqualTo(expectedStr);
    }
    static Stream<Arguments> generator() { // 2
        return Stream.of(
                Arguments.of(null, 10, "-", null), // T1
                Arguments.of("", 5, "-", "-----"), // T2
                Arguments.of("abc", -1, "-", "abc"), // T3
                Arguments.of("abc", 5, null, " abc"), // T4
                Arguments.of("abc", 5, "", " abc"), // T5
                Arguments.of("abc", 5, "-", "--abc"), // T6
                Arguments.of("abc", 3, "-", "abc"), // T7
                Arguments.of("abc", 0, "-", "abc"), // T8
                Arguments.of("abc", 2, "-", "abc") // T9
        );
    }
}
```
**Linea 1:**
Hace una prueba unitaria en base a los parametros del metodo leftPad de la clase LeftPadUtils
<br/>
**Linea 2:**
Se hace la prueba *generator()* con una lista de parametros.

**Analisis de Cobertura:**
![](img/ex13_jacoco_methods.png)
![](img/ex13_jacoco_code.png)


### Ejercicio 14
Agrega estos tres casos de prueba adicionales a la prueba parametrizada, como se
muestra en el listado, y vuelve a ejecutar la herramienta de cobertura. Explica el informe obtenido,
¿es similar al anterior? Explica tu respuesta.
```java
static Stream<Arguments> generator() {
    return Stream.of(
            // ... otros casos de prueba aquí
            Arguments.of("abc", 5, "--", "--abc"), // T10
            Arguments.of("abc", 5, "---", "--abc"), // T11
            Arguments.of("abc", 5, "-", "--abc") // T12
    );
}
```
Son similares a T6 debido a que *size = 5* y esto garantiza que el programa va directamente a la unica condicional de la clase LeftPadUtils donde *pads > padLen*

**Analisis de cobertura:**
![](img/ex14_jacoco_methods.png)
![](img/ex14_jacoco_code.png)


### Ejercicio 15:
Agrega este caso de prueba adicional a la prueba parametrizada y vuelve a ejecutar la
herramienta de cobertura. Explica el informe obtenido, ¿es similar al anterior? Explica tu respuesta.

```java
@Test
void sameInstance() {
    String str = "sometext";
    assertThat(LeftPadUtils.leftPad(str, 5, "-")).isSameAs(str);
}
```

![](img/ex14_jacoco_methods.png)
![](img/ex14_jacoco_code.png)

Rpta: Si, es similar a T3, T8 y T9 ya que la variable *size* que es 5 no es mayor que el tamaño de *str* que es "sometext" y el programa simplemente devuelve *str* sin cambio alguno.


### Ejercicio 16: Explica las líneas 1, 2 y 3 del codigo anterior.
```java
public class Clumps {
    /**
     * @param nums
     *
     * @return …
     */
    public static int countClumps(int[] nums) {
        if (nums == null || nums.length == 0) { // 1
            return 0;
        }
        int count = 0;
        int prev = nums[0];
        boolean inClump = false;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == prev && !inClump) { // 2
                inClump = true;
                count += 1;
            }
            if (nums[i] != prev) { // 3
                prev = nums[i];
                inClump = false;
            }
        }
        return count;
    }
}
```


### Ejercicio 17:
Escribe caso de prueba y vuelve a ejecutar la herramienta de cobertura. Explica el
informe obtenido Explica tu respuesta. ¿Se logra una cobertura de ramas del 100%?. ¿Se puede
confiar ciegamente en la cobertura?.

### Ejercicio 18: Explica las líneas 1, 2 y 3 del codigo anterior.

### Ejercicio 19:
Escribe caso de prueba y vuelve a ejecutar la herramienta de cobertura. Explica el
informe obtenido. Explica tu respuesta. ¿Se logra una cobertura de ramas del 100%? ¿Se puede
confiar ciegamente en la cobertura?

### Ejercicio 20: 
Agrega estos tres casos de prueba adicionales a la prueba parametrizada, como se
muestra en el listado, y vuelve a ejecutar la herramienta de cobertura. Explica el informe obtenido,
¿es similar al anterior? Explica tu respuesta.
### Ejercicio 21: 
Agrega este caso de prueba adicional a la prueba parametrizada y vuelve a ejecutar la
herramienta de cobertura. Explica el informe obtenido, ¿es similar al anterior? Explica tu respuesta.

Considera las siguientes pruebas unitarias:
### Ejercicio 22: Comprender las Pruebas de Mutación
Comprende los conceptos básicos y la terminología de las pruebas de mutación.
Instrucciones: <br/>
Define los siguientes términos:
- Mutante:
Es una versión modificada del programa original, creada introduciendo pequeños cambios llamados mutaciones. 
Normalmente suelen ser alteraciones simples en el código, como cambiar un operador aritmético, lógico o una constante. 
Los casos de prueba tienen que detectar estos cambios a fin de revelar la presencia de errores.
- Matar un mutante:
Ocurre cuando la salida del mutante es diferente a la del programa original cuando se ejecuta con el mismo conjunto de pruebas.
Esto indica que el caso de prueba es lo suficientemente efectivo como para identificar errores en el código.
- Sobrevivir un mutante:
Un mutante "sobrevive" si, al ser ejecutado con los casos de prueba existentes, produce la misma salida que el programa original.
Esto puede indicar que el caso de prueba no es lo suficientemente optimo o robusto para detectar ciertos tipos de errores.
- Cobertura de mutación:
Es una metrica la cual que indica la proporción de mutantes que han sido matados por el conjunto de pruebas, se calcula como *MutantesMatados/TotalMutantes*. Una alta cobertura de mutación sugiere que los casos de prueba son efectivos en detectar errores.

Explica las dos suposiciones principales que hacen las pruebas de mutación:
- Hipótesis del programador competente
- Efecto de acoplamiento

### Ejercicio 23: Introducción a PIT (Pitest)
Configurar y ejecutar pruebas de mutación utilizando PIT en un proyecto Java.

Calculator:
```java
public class Calculator {
    public int add(int a, int b) {
        return a + b;
    }
    public int subtract(int a, int b) {
        return a - b;
    }
    public int multiply(int a, int b) {
        return a * b;
    }
    public int divide(int a, int b) {
        if (b == 0) {
            throw new IllegalArgumentException("Divisor cannot be zero");
        }
        return a / b;
    }
}
```
Testing:
```java
public class CalculatorTest {
    @Test
    void testAdd() {
        Calculator calc = new Calculator();
        assertEquals(5, calc.add(2, 3));
    }
    @Test
    void testSubtract() {
        Calculator calc = new Calculator();
        assertEquals(1, calc.subtract(3, 2));
    }
    @Test
    void testMultiply() {
        Calculator calc = new Calculator();
        assertEquals(6, calc.multiply(2, 3));
    }
    @Test
    void testDivide() {
        Calculator calc = new Calculator();
        assertEquals(2, calc.divide(6, 3));
    }
    @Test
    void testDivideByZero() {
        Calculator calc = new Calculator();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> calc.divide(1, 0));
        assertEquals("Divisor cannot be zero", exception.getMessage());
    }
}
```
- Configura PIT en tu proyecto siguiendo las instrucciones en Pitest Quick Start.
```
plugins {
    id 'java'
    // https://gradle-pitest-plugin.solidsoft.info/
    id 'info.solidsoft.pitest' version '1.15.0'
    // https://docs.gradle.org/current/userguide/jacoco_plugin.html
    id 'jacoco' // Plugin de JaCoCo
}

group = 'com.jhaner'
version = '1.0-SNAPSHOT'

repositories {
    mavenCentral()
}

dependencies {
    testImplementation platform('org.junit:junit-bom:5.9.1')
    testImplementation 'org.junit.jupiter:junit-jupiter'
    testImplementation 'org.assertj:assertj-core:3.25.3'
    testImplementation 'org.jetbrains:annotations:24.0.0'
    // Agrega el plugin de PIT para JUnit 5
    pitest 'org.pitest:pitest-junit5-plugin:1.1.0'
}

test {
    useJUnitPlatform()
}

// pitest
pitest {
    targetClasses = ['org.example.*'] // Paquete de clases a mutar
    mutators = ['DEFAULTS'] // Conjunto de mutadores por defecto
    outputFormats = ['HTML'] // Formato de salida del informe
    timestampedReports = false // Deshabilitar informes con marca de tiempo para facilitar la navegación
}

// jacoco
jacoco {
    toolVersion = "0.8.12" // Versión de JaCoCo (compatible con java 21)
}

jacocoTestReport {
    dependsOn test // Ejecuta las pruebas antes de generar el informe

    reports {
        xml.required.set(true)
        html.required.set(true)
    }
}

/*
// Configuración de cobertura mínima requerida si no se cumple, el build fallará
jacocoTestCoverageVerification {
    violationRules {
        rule {
            limit {
                minimum = 0.8 // 80% de cobertura mínima requerida
            }
        }
    }
}
*/

check.dependsOn jacocoTestCoverageVerification

// ./gradlew pitest
// ./gradlew jacocoTestReport
```
Luego corremos *./gradlew pitest* y *./gradlew jacocoTestReport* en consola.
- Ejecuta PIT y genera un informe de mutación.
![](img/ex23_pitest_methods.png)
![](img/ex23_pitest_code.png)
![](img/ex_killed_survivers.png)

- Analiza el informe de mutación. ¿Cuántos mutantes fueron generados? ¿Cuántos mutantes
  fueron matados? ¿Cuántos sobrevivieron? ¿Qué mutantes sobrevivieron y por qué?


### Ejercicio 24: Mejorar el conjunto de pruebas
Mejorar el conjunto de pruebas basado en el informe de mutación
### Ejercicio 25: Crear Mutantes manualmente
Comprender cómo se generan los mutantes y probar manualmente si las pruebas los detectan.
### Ejercicio 26: Análisis detallado de mutantes

