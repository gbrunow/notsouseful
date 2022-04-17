package com.notsouseful.core;

import java.math.BigDecimal;

public class SafeBigDecimal {
    private final BigDecimal decimal;

    public SafeBigDecimal(String number) {
        BigDecimal temp = null;
        try {
            temp = new BigDecimal(number);
        } catch (NumberFormatException ex) {
            temp = null;
        } finally {
            this.decimal = temp;
        }
    }

    public BigDecimal getBigDecimal() {
        return this.decimal;
    }
}
