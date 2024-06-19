# Contratos

 ## Ejercicio 1

:question: **Pregunta**

Escribe el Javadoc del método calculateTax describiendo su contrato, en el código anterior.  
Revisa el archivo TaxCalculator.java

```java
public class TaxCalculator {

    public double calculateTax(double value) {
        //La precondición: un simple if para garantizar que no pasen valores no válidos
        if(value < 0) {
            throw new RuntimeException("Value has to be positive");
        }

        double taxValue = 0;

        // some complex business rule here...
        // final value goes to 'taxValue'

        //La postcondición también se implementa como un simple if. Si algo sale mal, lanzamos una excepción,
        //alertando al consumidor que la postcondición no se cumple
        if(taxValue < 0) {
            throw new RuntimeException("Calculated tax cannot be negative");
        }

        return taxValue;
    }
}

```

:white_check_mark: **Repuesta**

```java
/**
 * Calcula el impuesto a partir de un valor dado.
 * 
 * Precondición: el valor debe ser positivo
 * Si el valor es negativo, se lanzará una excepción
 * 
 * Postcondición: el valor del impuesto calculado no puede ser negativo
 * Si el valor del impuesto calculado es negativo, se lanzará una excepción
 * 
 * @param value el valor sobre el que se calculará el impuesto
 * @return el valor del impuesto calculado
 * @throws RuntimeException si el valor es negativo
 */
public double calculateTax(double value) {
    // codigo aqui ...
}
```

## Ejercicio 2

:question: **Pregunta**

Pregunta 2 Escribe una versión de TaxCalculator usando asserts para ello completa el archivo
TaxCalculator1.java

:white_check_mark: **Repuesta**

```java
public class TaxCalculator1 {

    public double calculateTax(double value) {
        // Precondición: el valor debe ser no negativo
        assert value >= 0 : "Value has to be positive";

        double taxValue = 0;

        // alguna regla de negocio compleja aquí...
        // el valor final va a 'taxValue'

        // Postcondición: el valor del impuesto debe ser no negativo
        assert taxValue >= 0 : "Calculated tax cannot be negative";

        return taxValue;
    }
}
```

## Ejercicio 3

:question: **Pregunta**

Una forma de evitar detener el programa debido a números negativos sería debilitar la precondición.
En otras palabras, en lugar de aceptar solo valores mayores que cero, el método podría aceptar
cualquier valor, positivo o negativo.

Listado 2 TaxCalculator con una precondición  más débil
```java
public double calculateTax(double value) {
    //No hay precondiciones para verificar,cualquier valor es válido
    // methods continues  
}
```

¿puedes aplicar el mismo razonamiento a las postcondiciones? , ¿como relacionas el
siguiente listado que devuelve un código de error en lugar de una excepción?

```java
public double calculateTax(double value) { 
// pre-condition check 
// Si la precondición  no se cumple, el método devuelve 0. El cliente de este método no necesita 
// preocuparse por las excepciones. 
 
 if (value < 0) { 
               return 0; 
 } 
 
  //… 
}
```

:white_check_mark: **Repuesta**

**¿puedes aplicar el mismo razonamiento a las postcondiciones?**

Sí, se puede aplicar el mismo razonamiento a las postcondiciones. 
Al igual que con las precondiciones, las postcondiciones también pueden ser más débiles o más fuertes.


```java
public double calculateTax(double value) {
    //No hay precondiciones para verificar,cualquier valor es válido
    // Simulación de lógica de cálculo de impuestos
    double taxValue = value * 0.1; // Ejemplo de cálculo simple: 10% del valor

    // Postcondición: el valor del impuesto debe ser no negativo
    if (taxValue < 0) {
        return 0;  // Código de error indicando que algo salió mal
    }

    return taxValue;
}
```

En el código anterior, si la postcondición no se cumple, el método devuelve 0.

**¿como relacionas el siguiente listado que devuelve un código de error en lugar de una excepción?**

En lugar de lanzar una excepción si la precondición no se cumple (valor negativo), 
el método puede devolver un código de error (0) que indique un problema, 
permitiendo que el cliente maneje el error sin detener el programa.


## Ejercicio 4

:question: **Pregunta**

**Listado 3 Clase Bsket**

```java
public class Basket {

    private BigDecimal totalValue = BigDecimal.ZERO;
    // Usamos BigDecimal en lugar de double para evitar problemas de redondeo en Java.
    private Map<Product, Integer> basket = new HashMap<>();

    public void add(Product product, int qtyToAdd) {
        // add the product 
        // update the total value 
    }

    public void remove(Product product) {
        // remove the product from the basket
        // update the total value
    }
}
```

Escribe para el método add() sus pre/postcondiciones.

:white_check_mark: **Repuesta**

```java
    public void add(Product product, int qtyToAdd) {
        // Precondición: product no debe ser null.
        if (product == null) {
            throw new IllegalArgumentException("El producto no puede ser null");
        }
        // Precondición: qtyToAdd debe ser mayor que 0.
        if (qtyToAdd <= 0) {
            throw new IllegalArgumentException("La cantidad a agregar debe ser mayor que 0");
        }

        // Agrega el producto al carrito.
        basket.put(product, basket.getOrDefault(product, 0) + qtyToAdd);

        // Actualiza el valor total del carrito.
        totalValue = totalValue.add(product.getPrice().multiply(BigDecimal.valueOf(qtyToAdd)));
    }
```

**Precondiciones:**

- product no debe ser null.
- qtyToAdd debe ser mayor que 0.

**Postcondiciones:**

- El producto debe estar en el carrito (basket) con la cantidad correcta (incrementada en qtyToAdd).
- totalValue debe actualizarse correctamente, reflejando el valor total de los productos en el carrito.

## Ejercicio 5

:question: **Pregunta**

Modelar otra postcondiciones aquí, como "el nuevo valor total debe ser mayor que el valor
total anterior". Usa  la clase BigDecimal en lugar de un double. BigDecimals se recomienda siempre que
desees evitar problemas de redondeo que pueden ocurrir cuando usas doubles

:white_check_mark: **Repuesta**

```java
    public void add(Product product, int qtyToAdd) {
        // Precondición: product no debe ser null.
        if (product == null) {
            throw new IllegalArgumentException("El producto no puede ser null");
        }
        // Precondición: qtyToAdd debe ser mayor que 0.
        if (qtyToAdd <= 0) {
            throw new IllegalArgumentException("La cantidad a agregar debe ser mayor que 0");
        }

        // Almacena el valor total anterior para la verificación de la postcondición.
        BigDecimal previousTotalValue = totalValue;

        // Agrega el producto al carrito.
        basket.put(product, basket.getOrDefault(product, 0) + qtyToAdd);

        // Actualiza el valor total del carrito.
        totalValue = totalValue.add(product.getPrice().multiply(BigDecimal.valueOf(qtyToAdd)));

        // Postcondicion Nueva: el nuevo valor total debe ser mayor que el valor total anterior.
        if (totalValue.compareTo(previousTotalValue) <= 0) {
            throw new IllegalStateException("El nuevo valor total debe ser mayor que el valor total anterior");
        }
    }

```

**Postcondiciones:**

- El producto debe estar en el carrito (basket) con la cantidad correcta (incrementada en qtyToAdd).
- totalValue debe actualizarse correctamente, reflejando el valor total de los productos en el carrito.
- **Nueva Postcondición** El nuevo valor total (totalValue) debe ser mayor que el valor total anterior (previousTotalValue).

## Ejercicio 6

:question: **Pregunta**

Escribe las pre/post condiciones del método remove().

Independientemente de los productos que se agreguen o eliminen de basket, el valor total de basket
nunca debe ser negativo. Esta no es una precondición ni una poscondición: es un invariante, y la clase
es responsable de mantenerlo.

:white_check_mark: **Repuesta**

```java
    public void remove(Product product) {
        // Precondición: product no debe ser null.
        if (product == null) {
            throw new IllegalArgumentException("El producto no puede ser null");
        }

        // Precondición: product debe existir en el carrito.
        if (!basket.containsKey(product)) {
            throw new IllegalArgumentException("El producto no está en el carrito");
        }

        // Almacena la cantidad del producto y su valor total antes de la eliminación.
        int qtyInBasket = basket.get(product);
        BigDecimal previousTotalValue = totalValue;

        // Elimina el producto del carrito.
        basket.remove(product);

        // Actualiza el valor total del carrito.
        totalValue = totalValue.subtract(product.getPrice().multiply(BigDecimal.valueOf(qtyInBasket)));

        // Postcondición: el valor total del carrito debe ser mayor o igual a cero.
        if (totalValue.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalStateException("El valor total del carrito no puede ser negativo");
        }
    }
```

**Precondiciones:**

- product no debe ser null.
- product debe existir en el carrito (basket).

**Postcondiciones:**

- El producto debe ser eliminado del carrito.
- totalValue debe actualizarse correctamente, reflejando el valor total de los productos en el carrito después de la eliminación.
- El valor total del carrito (totalValue) debe ser mayor o igual a cero (mantenido como un invariante).