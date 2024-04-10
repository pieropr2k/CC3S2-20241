package org.example;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class MoneyTest {
    //Clase de prueba: no implementa ninguna interfaz. Hay un acuerdo de que siempre termine en Test
    @Test
    void constructorShouldSetAmountAndCurrency() {
        // Método de prueba unitaria que está destinado a verificar el comportamiento del constructor de una clase money

        Money money = new Money(10, "USD"); //Se crea el SUT (sistema bajo prueba)
        // Assertions: verificacion real

        // El SUT de pone a prueba usando el método estático assertThat
        assertThat(money.getAmount()).isEqualTo(10);
        assertThat(money.getCurrency()).isEqualTo("USD");
    }

}