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




