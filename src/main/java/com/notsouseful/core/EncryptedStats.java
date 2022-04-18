package com.notsouseful.core;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EncryptedStats {
    private Stats stats;

    public EncryptedStats(Stats stats) {
        this.stats = stats;
    }

    @JsonProperty("average")
    public String getAverage() {
        return NSUCipher.getInstance()
                .encrypt(this.stats.getAverage() != null ? this.stats.getAverage().toPlainString() : "");
    }

    @JsonProperty("standardDeviation")
    public String getStandardDeviation() {
        return NSUCipher.getInstance().encrypt(this.stats.getStandardDeviation().toPlainString());
    }
}
