package com.atelier.tennis.util;

import java.util.List;

public final class MathUtils {

    private MathUtils() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static double round(double value, int decimals) {
        double scale = Math.pow(10, decimals);
        return Math.round(value * scale) / scale;
    }

    
    public static double median(List<Double> sortedValues) {
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
