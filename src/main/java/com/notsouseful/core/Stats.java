package com.notsouseful.core;

import java.math.BigDecimal;
import java.math.MathContext;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Stats {
    private BigDecimal average = null;
    private BigDecimal populationSize = BigDecimal.ZERO;
    private BigDecimal sumOfSquares = BigDecimal.ZERO;

    public Stats() {

    }

    public void addSample(BigDecimal sample) {
        this.populationSize = this.populationSize.add(BigDecimal.ONE);

        if (this.populationSize.equals(BigDecimal.ONE)) {
            this.average = sample;
        } else {
            this.average = this.populationSize
                    .subtract(BigDecimal.ONE)
                    .divide(this.populationSize, MathContext.DECIMAL128)
                    .multiply(this.average)
                    .add(sample.divide(this.populationSize, MathContext.DECIMAL128));
        }

        this.sumOfSquares = this.sumOfSquares.add(sample.pow(2));
    }

    @JsonProperty("average")
    public BigDecimal getAverage() {
        return this.average;
    }

    @JsonProperty("standardDeviation")
    public BigDecimal getStandardDeviation() {
        return this.populationSize.equals(BigDecimal.ZERO)
                ? BigDecimal.ZERO
                : this.sumOfSquares
                        .multiply(this.populationSize)
                        .subtract(this.average.multiply(this.populationSize).pow(2))
                        .divide(this.populationSize.pow(2), MathContext.DECIMAL128)
                        .sqrt(MathContext.DECIMAL128);
    }
}
