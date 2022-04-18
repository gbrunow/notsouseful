package com.notsouseful.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

public class TestSafeBigDecimal {
    @Test
    public void testThatValidNumberIsCreated() {
        final SafeBigDecimal valid = new SafeBigDecimal("12345");
        final BigDecimal expected = new BigDecimal("12345");

        assertTrue(expected.equals(valid.getBigDecimal()));
    }

    @Test
    public void testThatInvalidNumberResultsInNull() {
        final SafeBigDecimal invalid = new SafeBigDecimal("xxxxx");

        assertEquals(null, invalid.getBigDecimal());
    }
}
