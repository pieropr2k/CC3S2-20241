# Actividad: Mockito

### Agregar Mockito al proyecto
Primero, para que Mockito esté disponible en nuestro proyecto, actualizaremos el archivo de
compilación para incluir Mockito. El siguiente ejemplo usa Gradle como herramienta de compilación,
pero la versión Maven usa las mismas coordenadas para las dependencias JUnit 5 y Mockito.
Puedes utilizar el archivo libs.versions.toml

### Ejercicio 2

Explica qué hacen las líneas 1 y 2 del código. . Presenta un informe generado por JaCoCo
(www.jacoco.org/jacoco) u otra herramienta de cobertura de código de tu preferencia en el IDE del
curso.

```
[versions]
assertj = "3.25.3"
gson = "2.10.1"
jackson = "2.17.0"
junit = "5.11.0-M1"
junit-platform = "1.11.0-M1"
mockito = "5.11.0"
retrofit = "2.11.0"
[libraries]
assertj = { module = "org.assertj:assertj-core", version.ref = "assertj" }
gson = { module = "com.google.code.gson:gson", version.ref = "gson" }
jackson = { module = "com.fasterxml.jackson.core:jackson-databind", version.ref = "jackson" }
junit-jupiter = { module = "org.junit.jupiter:junit-jupiter", version.ref = "junit" }
junit-platform = { module = "org.junit.platform:junit-platform-launcher", version.ref = "junit-platform" }
junit-vintage = { module = "org.junit.vintage:junit-vintage-engine", version.ref = "junit" }
mockito-core = { module = "org.mockito:mockito-core", version.ref = "mockito" }
mockito-junit = { module = "org.mockito:mockito-junit-jupiter", version.ref = "mockito" }
retrofit-core = { module = "com.squareup.retrofit2:retrofit", version.ref = "retrofit" }
retrofit-gson = { module = "com.squareup.retrofit2:converter-gson", version.ref = "retrofit" }


[bundles]
junit = [
 "junit-jupiter",
 "junit-platform",
 "junit-vintage",
]
mockito = [
 "mockito-core",
 "mockito-junit",
]
[plugins]
version-catalog-update = "nl.littlerobots.version-catalog-update:0.8.4"
versions = "com.github.ben-manes.versions:0.51.0"
```
### Ejercicio: Utiliza este archivo y configura el settings.gradle de la siguiente manera
```
rootProject.name = 'Actividad-Mockito'


dependencyResolutionManagement {
    versionCatalogs {
        libs {
            from(files("gradle/libs.versions.toml"))
        }
    }
}

```

### Ejercicio 1: Simulación de excepciones
Crea una prueba que simule una excepción lanzada por el PersonRepository y asegúrate de que tu
código maneje la excepción correctamente.
- Modifica HelloMockito para que maneje excepciones lanzadas por PersonRepository.
```java
public class HelloMockito {
    private String greeting = "Hello, %s, from Mockito!";
    // Dependencies
    private final PersonRepository personRepository;
    private final TranslationService translationService;
    // Constructor to inject the dependencies
    public HelloMockito(PersonRepository personRepository, TranslationService translationService) {
        this.personRepository = personRepository;
        this.translationService = translationService;
    }
    // Method we want to test
    public String greet(int id, String sourceLang, String targetLang) {
        String name;
        try {
            Optional<Person> person = personRepository.findById(id);
            name = person.map(Person::getFirst).orElse("World");
        } catch (Exception e) {
            name = "World";
        }
        return translationService.translate(String.format(greeting, name), sourceLang, targetLang);
    }
}
```

- Crea un nuevo método de prueba greetPersonThrowsException en HelloMockitoTestFull.java
para simular una excepción y verificar el comportamiento del método greet.
```java
    @Test
    @DisplayName("Greet a person not in the database")
    void greetPersonThrowsException() {
        int id = 1;
        String sourceLang = "en";
        String targetLang = "es";
        // Simula excepcion de personRepository.findById
        when(repository.findById(id)).thenThrow(new RuntimeException("Database error"));
        // llama a greet() y verifica el retorno del resultado
        String result = helloMockito.greet(id, sourceLang, targetLang);
        // Verificamos resultado
        assertEquals("Error occurred while retrieving the person", result);
    }
```

### Ejercicio 2: Verificación de llamadas a métodos
Asegúrate de que el método translate no se llame si PersonRepository lanza una excepción.
- Modifica HelloMockito para que no llame a translationService.translate si ocurre una excepción.
- Crea una prueba para verificar este comportamiento.

```java
public class HelloMockito {
    private String greeting = "Hello, %s, from Mockito!";
    // Dependencies
    private final PersonRepository personRepository;
    private final TranslationService translationService;
    // Constructor to inject the dependencies
    public HelloMockito(PersonRepository personRepository, TranslationService translationService) {
        this.personRepository = personRepository;
        this.translationService = translationService;
    }
    // Method we want to test
    public String greet(int id, String sourceLang, String targetLang) {
        String name;
        try {
            Optional<Person> person = personRepository.findById(id);
            name = person.map(Person::getFirst).orElse("World");
            return translationService.translate(String.format(greeting, name), sourceLang, targetLang);
        } catch (Exception e) {
            return "Error occurred while retrieving the person";
        }
    }
}
```

```java
    @Test
    public void greetPersonThrowsExceptionAndBlocksTranslate() {
        int id = 1;
        String sourceLang = "en";
        String targetLang = "es";
        when(repository.findById(id)).thenThrow(new RuntimeException("Database error"));
        // Llamamos a greet
        String result = helloMockito.greet(id, sourceLang, targetLang);
        // Comparamos
        assertEquals("Error occurred while retrieving the person", result);
        // Verificamos que translationService.translate no ha sido llamado
        verify(translationService, never()).translate(anyString(), anyString(), anyString());
    }
```

### Ejercicio 3: Prueba de traducción en diferentes idiomas
Asegúrate de que el servicio de traducción funciona correctamente para diferentes combinaciones de
idiomas.

- Modifica HelloMockito para incluir más lógica de traducción.
```java

```
- Crea pruebas que verifiquen la traducción a diferentes idiomas.
```java

```

### Ejercicio 4: Uso de AssertJ para verificaciones más complejas
Utiliza AssertJ para realizar verificaciones más complejas en tus pruebas, como verificaciones en listas o
en objetos anidados.
- Añade un método en HelloMockito que retorne una lista de saludos para múltiples personas.
```java

```
- Crea pruebas que verifiquen la lista de saludos utilizando AssertJ.
```java

```

## Parte 2: Contando astronautas por nave espacial
