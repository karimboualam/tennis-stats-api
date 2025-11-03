package com.atelier.tennis.util;

import org.junit.jupiter.api.Test;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Tests unitaires de la classe {@link MathUtils}.
 *
 * Vérifie le bon fonctionnement des méthodes utilitaires de calcul
 * telles que {@code round} et {@code median}, ainsi que le comportement
 * du constructeur privé.
 *
 * @author Karim BOUALAM
 * @version 1.0.0
 * @since 2025-11
 */
class MathUtilsTest {

 @Test
void constructorShouldThrowException() throws Exception {
    var constructor = MathUtils.class.getDeclaredConstructor();
    constructor.setAccessible(true);

    // WHEN + THEN
    Exception exception = assertThrows(Exception.class, constructor::newInstance);
    assertThat(exception)
            .isInstanceOf(java.lang.reflect.InvocationTargetException.class)
            .hasCauseInstanceOf(UnsupportedOperationException.class)
            .cause()
            .hasMessage("Utility class");
}

    @Test
    void roundShouldReturnRoundedValue() {
        // GIVEN
        double value = 3.14159;

        // WHEN
        double result = MathUtils.round(value, 2);

        // THEN
        assertThat(result).isEqualTo(3.14);
    }

    @Test
    void roundShouldHandleZeroDecimals() {
        double result = MathUtils.round(5.67, 0);
        assertThat(result).isEqualTo(6.0);
    }

    @Test
    void medianShouldReturnMiddleValueForOddList() {
        // GIVEN
        List<Double> values = List.of(1.0, 2.0, 3.0, 4.0, 5.0);

        // WHEN
        double result = MathUtils.median(values);

        // THEN
        assertThat(result).isEqualTo(3.0);
    }

    @Test
    void medianShouldReturnAverageForEvenList() {
        // GIVEN
        List<Double> values = List.of(1.0, 2.0, 3.0, 4.0);

        // WHEN
        double result = MathUtils.median(values);

        // THEN
        assertThat(result).isEqualTo(2.5);
    }

    @Test
    void medianShouldReturnZeroForEmptyList() {
        // GIVEN
        List<Double> empty = List.of();

        // WHEN
        double result = MathUtils.median(empty);

        // THEN
        assertThat(result).isZero();
    }

    @Test
    void medianShouldReturnZeroForNullList() {
        // WHEN
        double result = MathUtils.median(null);

        // THEN
        assertThat(result).isZero();
    }
}
