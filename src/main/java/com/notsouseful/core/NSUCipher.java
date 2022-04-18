package com.notsouseful.core;

import java.security.GeneralSecurityException;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;

public class NSUCipher {
    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    private static NSUCipher instance = null;

    private SecretKey key;
    private Cipher encrypter;

    private static final int KEY_SIZE = 128;
    private static final int T_LEN = 128;
    private static final String KEY_GEN_ALGORITHM = "AES";
    private static final String CIPHER_ALGORITHM = "AES/GCM/NoPadding";
    private static final String SEPARATOR = ",";

    private NSUCipher() {
        try {
            KeyGenerator generator = KeyGenerator.getInstance(KEY_GEN_ALGORITHM);
            generator.init(KEY_SIZE);
            key = generator.generateKey();
        } catch (GeneralSecurityException ignored) {
            LOGGER.log(Level.SEVERE, ignored.toString());
        }
    }

    public static NSUCipher getInstance() {
        if (instance == null) {
            instance = new NSUCipher();
        }

        return instance;
    }

    public String encrypt(String message) {
        String encrypted = "";
        String iv = "";

        try {
            encrypter = Cipher.getInstance(CIPHER_ALGORITHM);
            encrypter.init(Cipher.ENCRYPT_MODE, key);
            encrypted = encode(encrypter.doFinal(message.getBytes()));
            iv = encode(encrypter.getIV());
        } catch (GeneralSecurityException ex) {
            LOGGER.log(Level.SEVERE, ex.toString());
        }

        return iv + SEPARATOR + encrypted;
    }

    public String decrypt(String message) {
        String decrypted = "";

        try {
            System.out.println(message);
            String parts[] = message.split(SEPARATOR, 2);
            String iv = parts[0];
            String encrypted = parts[1];

            Cipher decrypter = Cipher.getInstance(CIPHER_ALGORITHM);
            GCMParameterSpec spec = new GCMParameterSpec(T_LEN, decode(iv));
            decrypter.init(Cipher.DECRYPT_MODE, key, spec);
            decrypted = new String(decrypter.doFinal(decode(encrypted)));
        } catch (GeneralSecurityException ex) {
            LOGGER.log(Level.SEVERE, ex.toString());
        }

        return decrypted;
    }

    private String encode(byte[] data) {
        return Base64.getEncoder().encodeToString(data);
    }

    private byte[] decode(String data) {
        return Base64.getDecoder().decode(data);
    }
}
