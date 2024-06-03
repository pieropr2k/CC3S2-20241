# Actividad - Refactorización

Esta es la clase inicial, se nos pide refactorizar el codigo aplicando los principios de SOLID

```java
public class BlogManager {
    private List<String> articles = new ArrayList<>();

    public void addArticle(String article) {
        if (article != null && !article.isEmpty()) {
            articles.add(article);
            System.out.println("Artículo añadido: " + article);
            saveArticleToDatabase(article);
        }
    }
    private void saveArticleToDatabase(String article) {
        System.out.println("Guardando en la base de datos: " + article);
    }
    public void printAllArticles() {
        for (String article : articles) {
            System.out.println("Artículo: " + article);
        }
    }
}
```

**Principio de Responsabilidad Unica (SRP):** Este principio dice que cada clase debe tener su propio proposito. Asi que separaremos el codigo anterior en 2 clases: la clase BlogManager lo que hace es administrar la lista de articulos (articles) esto lo hace mediante el metodo addArticle() para añadir cada articulo y printAllArticles() para imprimir y ver los datos de cada articulo, y la clase MySQLConnection su proposito es guardar el articulo agregado en la base de datos y esto lo hace con el metodo saveArticle().
Ademas vamos a añadirle un contructor a la clase BlogManager para tener un atributo de la clase MySQLConnection para que esta haga su trabajo con su metodo previamente mencionado. <br/>
Este principio estara en el código del Sprint1:

```java
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
        System.out.println("Guardando en la base de datos: " + article);
    }
}
```

**Principio de Abierto/Cerrado (OCP):** En la clase BlogManager si bien no hay la necesidad de escribir un condicional if-else el cual rompe con este principio (y es un ejemplo comun al mostrar la utilidad de este principio). Al añadirle a esta clase un atributo de la clase DBConnection, esto hace que BlogManager ahora este abierto para la extensión y cerrado para la modificación, ya que podemos cambiar la implementación de DBConnection sin modificar BlogManager.

```java
public interface DBConnection {
    void saveArticle(String article);
}

public class MySQLConnection implements DBConnection {
    @Override
    public void saveArticle(String article) {
        System.out.println("Guardando en la base de datos: " + article);
    }
}
```
**Principio de Inversion de Dependencias (DIP):** En el codigo de arriba se crea la interfaz DBConnection para que la clase BlogManager (modulo de alto nivel) no dependa directamente de MySQLConnection (modulo de bajo nivel) sino dependa de su abstraccion la cual es la interfaz creada. <br/>
Esta modificacion estara en la parte de Sprint2.

Estos son otros principios:

**Principio de Sustitución de Liskov (LSP):** Indica que los objetos de una clase derivada deben ser sustituibles por objetos de su clase base sin alterar el comportamiento correcto del programa. Sin embargo en nuestro analisis no es necesario hacer una subclase de alguna de las mencionadas.

**Principio de Segregación de Interfaz (ISP)**: Este principio indica que una clase cliente no debe ser forzada a usar métodos los cuales no va a usar.

Ya que nos quedamos con el mal sabor por no poder usar estos 2 principios ultimamente mencionados, extenderemos el codigo. Este código estará en la parte de Sprint3.

**Principio de Segregación de Interfaz:** Vamos a extender el codigo para aprovechar este principio.
Para esto crearemos la funcion deleteArticle() para que BlogManager tenga algunas de las operaciones CRUD.

Hasta acá todo bien, ¿pero que tal si queremos usar la Blockchain como Base de Datos? Preferiblemente algo como Ethereum.
Hay que considerar que en el mundo Blockchain al hacer operaciones delete no se puede eliminar información de manera absoluta ya que tiene naturaleza inmutable. <br/>
Para esto crearemos la clase EthBlockchainConnection:

```java
public interface DBConnection {
    void saveArticle(String article);
    void deleteArticle(String article);
}

public class EthBlockchainConnection implements DBConnection {
    @Override
    public void saveArticle(String article) {
        System.out.println("Guardando en la base de datos: " + article);
    }
}
```

Sin embargo EthBlockchainConnection lanzara errores ya que no esta implementando el metodo deleteArticle(), esto ademas de romper el programa rompe el Principio de Segregación de Interfaz.
Para respetar este principio crearemos la clase DeletableDBConnection la cual extendera DBConnection y añadera un nuevo metodo el cual es deleteArticle, para que asi la clase EthBlockchainConnetion no se vea forzada a implementar el metodo deleteArticle().

```java
public interface DBConnection {
    void saveArticle(String article);
}

public interface DeleteableDBConnection extends DBConnection {
    void deleteArticle(String article);
}

public class MySQLConnection implements DeleteableDBConnection {
    @Override
    public void saveArticle(String article) {
        System.out.println("Guardando en la base de datos: " + article);
    }

    @Override
    public void deleteArticle(String article) {
        System.out.println("Eliminando de la base de datos: " + article);
    }
}

public class EthBlockchainConnection implements DBConnection {
    @Override
    public void saveArticle(String article) {
        System.out.println("Guardando en la base de datos: " + article);
    }
}
```

**Principio de Sustitucion de Liskov:** este principio menciona que si tienes una clase hija, esta clase hija no debe eliminar comportamiento de la clase padre y puede sustituirla. En este caso crearemos el metodo DeletableBlogManager ya que se quiere crear un metodo delete el cual BlogManager no tiene, para asi sustituirla en caso se quiera eliminar un articulo.

```java
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
    
    public DeletableBlogManager(DeletableDBConnection deletableArticleDatabase) {
        super(deletableArticleDatabase);
        this.deletableArticleDatabase = deletableArticleDatabase;
    }

    public void deleteArticle(String article) {
        if (article != null) {
            System.out.println("Artículo eliminado: " + article);
            super.getArticles().remove(article);
        }
    }
}
```

Este es el código final y esta de manera organizada en la carpeta Sprint3:

```java
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
    
    public DeletableBlogManager(DeletableDBConnection deletableArticleDatabase) {
        super(deletableArticleDatabase);
        this.deletableArticleDatabase = deletableArticleDatabase;
    }

    public void deleteArticle(String article) {
        if (article != null) {
            System.out.println("Artículo eliminado: " + article);
            super.getArticles().remove(article);
        }
    }
}

public interface DBConnection {
    void saveArticle(String article);
}

public interface DeletableDBConnection extends DBConnection {
    void deleteArticle(String article);
}

public class MySQLConnection implements DeletableDBConnection {
    @Override
    public void saveArticle(String article) {
        System.out.println("Guardando en la base de datos: " + article);
    }

    @Override
    public void deleteArticle(String article) {
        System.out.println("Eliminando de la base de datos: " + article);
    }
}

public class EthBlockchainConnection implements DBConnection {
    @Override
    public void saveArticle(String article) {
        System.out.println("Guardando en la base de datos: " + article);
    }
}
```