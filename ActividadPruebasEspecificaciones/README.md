**Ejercicio 1:**

Se nos pide hacer un codigo de prueba considerando las entradas str = "axcaycazc", open = "a" y close
= "c".
El codigo de la prueba unitaria es este:

```
    @Test
    void exercise1Case() {
        assertThat(StringUtils.substringsBetween("axcaycazc", "a", "c")).isEqualTo(new String[] {  });
    }
```
Este es la prueba ejecutada: <br/>
![](img/exercise1.png)

De momento estamos en fase roja
Al hacer la prueba se ve que no pasa y se devuelven un conjunto de variables.
Esto indica que lo que hace el codigo es de un string (str) ve el conjunto de caracteres que esta entre los caracteres *open* y *close*.

Para que este en fase verde lo que haremos es poner las variables devueltas por la consola en nuestra prueba unitaria.

```
    @Test
    void exercise1Case() {
        assertThat(StringUtils.substringsBetween("axcaycazc", "a", "c")).isEqualTo(new String[] { "x", "y", "z" });
    }
```

Con esto pasamos a la fase verde ya que la prueba pasa.

**Ejercicio 2:**

```
import java.util.ArrayList;
import java.util.List;

public class BlogManager {
    private List<String> articles = new ArrayList<>();
    private MySQLConnection articleDatabase;

    public BlogManager(MySQLConnection articleDB) {
        this.articleDatabase = articleDatabase;
    }

    public void addArticle(String article) {
        if (article != null && !article.isEmpty()) {
            articles.add(article);
            System.out.println("Artículo añadido: " + article);
            articleDatabase.saveArticle(article);
        }
    }

    public void printAllArticles() {
        for (String article : articles) {
            System.out.println("Artículo: " + article);
        }
    }
}

public class MySQLConnection {
    public void saveArticle(String article) {
        // Simulación de guardar en la base de datos
        System.out.println("Guardando en la base de datos: " + article);
    }
}
```

**Principio de Abierto/Cerrado (OCP):** En la clase BlogManager si bien no hay la necesidad de escribir un condicional if-else el cual rompe con este principio (y es un ejemplo comun al mostrar la utilidad de este principio). Al añadirle a esta clase un atributo de la clase DBConnection, esto hace que BlogManager ahora este abierto para la extensión y cerrado para la modificación, ya que podemos cambiar la implementación de DBConnection sin modificar BlogManager.

```
public interface DBConnection {
    void saveArticle(String article);
}

public class MySQLConnection implements DBConnection {
    @Override
    public void saveArticle(String article) {
        // Simulación de guardar en la base de datos
        System.out.println("Guardando en la base de datos: " + article);
    }
}
```


**Liskov Substitution Principle (LSP):** Indica que los objetos de una clase derivada deben ser sustituibles por objetos de su clase base sin alterar el comportamiento correcto del programa. Sin embargo en nuestro analisis no es necesario hacer una subclase de alguna de las mencionadas.

**Dependency Inversion Principle (DIP):** Se crea la interfaz ArticleRepository para que la clase BlogManager (modulo de alto nivel) no dependa directamente de xArticleRepository (modulo de bajo nivel) sino dependa de su abstraccion la cual es la interfaz creada.


Ya que nos quedamos con el mal sabor por no poder usar el Principio de Segregación de Interfaz ni el Principio de Sustitucion de Liskov, extenderemos el codigo.

Principio de Segregación de Interfaz: Vamos a extender el codigo para aprovechar este principio.
Para esto crearemos la funcion deleteArticle() para que BlogManager tenga algo de las operaciones CRUD (sin update).

Hasta acá todo bien, ¿pero que tal si queremos usar la Blockchain como Base de Datos? Preferiblemente algo como Ethereum.
Hay que considerar que en el mundo Blockchain al hacer operaciones delete no se puede eliminar información de manera absoluta ya que tiene naturaleza inmutable.
Para esto crearemos la clase EthBlockchainConnection

```
public interface DBConnection {
    void saveArticle(String article);
    void deleteArticle(String article);
}

public class EthBlockchainConnection implements DBConnection {
    @Override
    public void saveArticle(String article) {
        // Simulación de guardar en la base de datos
        System.out.println("Guardando en la base de datos: " + article);
    }
}
```

Sin embargo EthBlockchainConnection lanzara errores ya que no esta implementando el metodo deleteArticle(), esto ademas de romper el programa rompe el Principio de Segregación de Interfaz.
Para respetar este principio crearemos la clase DeletableDBConnection la cual extendera DBConnection y añadera un nuevo metodo el cual es deleteArticle, para que asi la clase EthBlockchainConnetion no se vea forzada a implementar el metodo deleteArticle().

```
public interface DBConnection {
    void saveArticle(String article);
}

public interface DeleteableDBConnection extends DBConnection {
    void deleteArticle(String article);
}

public class MySQLConnection implements DeleteableDBConnection {
    @Override
    public void saveArticle(String article) {
        // Simulación de guardar en la base de datos
        System.out.println("Guardando en la base de datos: " + article);
    }

    @Override
    public void deleteArticle(String article) {
        // Simulación de guardar en la base de datos
        System.out.println("Guardando en la base de datos: " + article);
    }
}

public class EthBlockchainConnection implements DBConnection {
    @Override
    public void saveArticle(String article) {
        // Simulación de guardar en la base de datos
        System.out.println("Guardando en la base de datos: " + article);
    }
}
```

**Principio de Sustitucion de Liskov:** este principio menciona que si tienes una clase hija, esta clase hija no debe eliminar comportamiento de la clase padre y puede sustituirla. En este caso crearemos el metodo DeletableBlogManager ya que se quiere crear un metodo delete el cual BlogManager no tiene, para asi sustituirla en caso se quiera eliminar un articulo.

```
import java.util.ArrayList;
import java.util.List;

public class BlogManager {
    private List<String> articles = new ArrayList<>();
    private DBConnection articleDatabase;

    public BlogManager(DBConnection articleDatabase) {
        this.articleDatabase = articleDatabase;
    }

    public void addArticle(String article) {
        if (article != null && !article.isEmpty()) {
            articles.add(article);
            System.out.println("Artículo añadido: " + article);
            articleDatabase.saveArticle(article);
        }
    }

    public void printAllArticles() {
        for (String article : articles) {
            System.out.println("Artículo: " + article);
        }
    }
}

public class DeletableBlogManager extends BlogManager{
    private DeletableDBConnection deletableArticleDatabase;

    // Constructor para repositorios que soportan guardar y eliminar artículos
    public BlogManager(DeletableDBConnection deletableArticleDatabase) {
        this.deletableArticleDatabase = deletableArticleDatabase;
        this.articleDatabase = deletableArticleDatabase;
    }

    public void deleteArticle(String article) {
        if (article != null) {
            System.out.println("Artículo eliminado: " + article);
            articles.remove(article);
        }
    }
}

public interface DBConnection {
    void saveArticle(String article);
}

public interface NonBlockchainDBConnection {
    void deleteArticle(String article);
}

public class MySQLConnection implements DBConnection, NonBlockchainDBConnection {
    @Override
    public void saveArticle(String article) {
        // Simulación de guardar en la base de datos
        System.out.println("Guardando en la base de datos: " + article);
    }

    @Override
    public void deleteArticle(String article) {
        // Simulación de guardar en la base de datos
        System.out.println("Guardando en la base de datos: " + article);
    }
}

public class EthBlockchainConnection implements DBConnection {
    @Override
    public void saveArticle(String article) {
        // Simulación de guardar en la base de datos
        System.out.println("Guardando en la base de datos: " + article);
    }
}
```