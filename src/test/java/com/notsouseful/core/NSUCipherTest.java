package com.notsouseful.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

public class NSUCipherTest {
    @Test
    public void testThatEncriptionResultsInDifferentNonEmptyString() {
        String message = "not so useful message";
        String encryptedMessage = NSUCipher.getInstance().encrypt(message);

        assertNotEquals(message, encryptedMessage);
        assertNotEquals("", encryptedMessage);
        assertNotNull(encryptedMessage);
    }

    @Test
    public void testThatDecriptionReversesEncription() {
        String message = "not so useful message";

        String encryptedMessage = NSUCipher.getInstance().encrypt(message);
        String decriptedMessage = NSUCipher.getInstance().decrypt(encryptedMessage);

        assertEquals(message, decriptedMessage);
    }

    @Test
    public void testThatDecriptionReversesSubsequentEncryptions() {
        String message = "not so useful message";

        String encryptedMessage1 = NSUCipher.getInstance().encrypt(message);
        String encryptedMessage2 = NSUCipher.getInstance().encrypt(message);

        String decriptedMessage = NSUCipher.getInstance().decrypt(encryptedMessage1);
        String decriptedMessage2 = NSUCipher.getInstance().decrypt(encryptedMessage2);

        assertEquals(message, decriptedMessage);
        assertEquals(message, decriptedMessage2);
    }
}
