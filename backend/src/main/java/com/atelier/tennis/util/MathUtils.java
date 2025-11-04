package com.atelier.tennis.util;

import java.util.List;

/**
 * Classe utilitaire contenant des méthodes mathématiques génériques.
 *
 * Cette classe regroupe des outils simples pour les calculs statistiques
 * utilisés dans l’application, tels que l’arrondi et la médiane.
 *
 * Elle est déclarée {@code final} et possède un constructeur privé pour
 * empêcher toute instanciation, conformément aux bonnes pratiques des
 * classes utilitaires.
 *
 * @author Karim BOUALAM
 * @version 1.0.0
 * @since 2025-11
 */
public final class MathUtils {

    /**
     * Constructeur privé pour empêcher l’instanciation.
     *
     * @throws UnsupportedOperationException toujours levée pour bloquer la création d’une instance
     */
    private MathUtils() {
        throw new UnsupportedOperationException("Utility class");
    }

    /**
     * Arrondit une valeur décimale à un nombre donné de chiffres après la virgule.
     *
     * <pre>
     * Exemple :
     * MathUtils.round(3.14159, 2) → 3.14
     * </pre>
     *
     * @param value    la valeur à arrondir
     * @param decimals le nombre de décimales souhaité
     * @return la valeur arrondie à {@code decimals} chiffres après la virgule
     */
    public static double round(final double value, final int decimals) {
        double scale = Math.pow(10, decimals);
        return Math.round(value * scale) / scale;
    }

    /**
     * Calcule la médiane d’une liste triée de valeurs numériques.
     *
     * Si la liste contient un nombre pair d’éléments, la médiane est la
     * moyenne des deux valeurs centrales.
     *
     * <pre>
     * Exemple :
     * MathUtils.median(List.of(1.0, 2.0, 3.0)) → 2.0
     * MathUtils.median(List.of(1.0, 2.0, 3.0, 4.0)) → 2.5
     * </pre>
     *
     * @param sortedValues la liste triée de valeurs numériques (croissantes)
     * @return la valeur médiane, ou {@code 0.0} si la liste est vide ou nulle
     */
    public static double median(final List<Double> sortedValues) {
        if (sortedValues == null || sortedValues.isEmpty()) {
            return 0.0;
        }

        int size = sortedValues.size();
        if (size % 2 == 0) {
            return (sortedValues.get(size / 2 - 1) + sortedValues.get(size / 2)) / 2.0;
        } else {
            return sortedValues.get(size / 2);
        }
    }
}
