package com.notsouseful.core;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestStats {
    @Test
    public void testThatInitialValuesAreCorrect() {
        final Stats stats = new Stats();
        assertEquals(null, stats.getAverage());
        assertEquals(BigDecimal.ZERO, stats.getStandardDeviation());
    }

    @Test
    public void testThatAddingSampleUpdatesStatsProperly() {
        final Stats stats = new Stats();

        final BigDecimal matrix[][] = new BigDecimal[][] {
                // sample, average, standard deviation
                { new BigDecimal("156"), new BigDecimal("156.0000000000"), new BigDecimal("0.0000000000") },
                { new BigDecimal("95"), new BigDecimal("125.5000000000"), new BigDecimal("30.5000000000") },
                { new BigDecimal("93"), new BigDecimal("114.6666666667"), new BigDecimal("29.2384830128") },
                { new BigDecimal("93"), new BigDecimal("109.2500000000"), new BigDecimal("27.0034719990") },
                { new BigDecimal("89"), new BigDecimal("105.2000000000"), new BigDecimal("25.4746933249") },
                { new BigDecimal("83"), new BigDecimal("101.5000000000"), new BigDecimal("24.6829900944") },
                { new BigDecimal("81"), new BigDecimal("98.5714285714"), new BigDecimal("23.9514815698") },
                { new BigDecimal("70"), new BigDecimal("95.0000000000"), new BigDecimal("24.3156328316") },
                { new BigDecimal("66"), new BigDecimal("91.7777777778"), new BigDecimal("24.6701699214") },
                { new BigDecimal("65"), new BigDecimal("89.1000000000"), new BigDecimal("24.7444943371") },
        };

        for (BigDecimal row[] : matrix) {
            BigDecimal sample = row[0];
            BigDecimal expectedAverage = row[1];
            BigDecimal expectedStdDev = row[2];

            stats.addSample(sample);
            assertEquals(
                    expectedAverage.setScale(5, RoundingMode.HALF_UP),
                    stats.getAverage().setScale(5, RoundingMode.HALF_UP));
            assertEquals(
                    expectedStdDev.setScale(5, RoundingMode.HALF_UP),
                    stats.getStandardDeviation().setScale(5, RoundingMode.HALF_UP));
        }
    }
}
