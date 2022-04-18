package com.notsouseful.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

public class EncryptedStatsTest {

    @Test
    public void testThatEncryptedIsDifferent() {
        final Stats stats = new Stats();
        stats.addSample(new BigDecimal("10"));
        final EncryptedStats encrypted = new EncryptedStats(stats);

        assertEncryptedAndRawValuesAreDifferent(stats, encrypted);
        assertEncryptedAndRawValuesAreEquals(stats, encrypted);
    }

    private void assertEncryptedAndRawValuesAreDifferent(Stats stats, EncryptedStats encrypted) {
        assertNotEquals(stats.getAverage().toPlainString(), encrypted.getAverage());
        assertNotEquals(stats.getStandardDeviation().toPlainString(), encrypted.getStandardDeviation());
    }

    private void assertEncryptedAndRawValuesAreEquals(Stats stats, EncryptedStats encrypted) {
        String decryptedAvg = NSUCipher.getInstance().decrypt(encrypted.getAverage());
        String decryptedStdDev = NSUCipher.getInstance().decrypt(encrypted.getStandardDeviation());

        assertEquals(stats.getAverage().toPlainString(), decryptedAvg);
        assertEquals(stats.getStandardDeviation().toPlainString(), decryptedStdDev);
    }
}
