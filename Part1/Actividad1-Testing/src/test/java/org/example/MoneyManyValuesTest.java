package org.example;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MoneyManyValuesTest {
    public static final String USD = "USD";
    @Test
    void constructorShouldSetAmountAndCurrency() {
        Money money = new Money(10, USD);
        // Assertions: verificacion real
        assertThat(money.getAmount()).isEqualTo(10);
        assertThat(money.getCurrency()).isEqualTo(USD);
        money = new Money(20, USD);
        assertThat(money.getAmount()).isEqualTo(20);
        assertThat(money.getCurrency()).isEqualTo(USD);
        // viola el principio DRY: osea tratar de no copiar y pegar
        // esta repitiendo codigo


        // se puede introducir algo de codigo de logica en tu prueba?
        // frameworks de prueba ofrecen soporte para esos casos
        // y se llama pruebas parametrizadas
    }

}
