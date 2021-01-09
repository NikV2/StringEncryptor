package me.nik.stringencryptor.utils;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public final class CryptUtils {

    private CryptUtils(){}

    public static String encrypt(final String valueToEnc, final byte[] bytes) {
        if (bytes.length < 16) return "The key needs to have a length of 16";

        try {
            Key key = new SecretKeySpec(bytes, "AES");
            Cipher c = Cipher.getInstance("AES");
            c.init(Cipher.ENCRYPT_MODE, key);
            byte[] encValue = c.doFinal(valueToEnc.getBytes());
            return Base64.getEncoder().encodeToString(encValue);
        } catch (NoSuchAlgorithmException | InvalidKeyException | NoSuchPaddingException | BadPaddingException | IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String decrypt(final String encryptedValue, final byte[] bytes) {
        if (bytes.length < 16) return "The key needs to have a length of 16";

        try {
            Key key = new SecretKeySpec(bytes, "AES");
            Cipher c = Cipher.getInstance("AES");
            c.init(Cipher.DECRYPT_MODE, key);
            byte[] decordedValue = Base64.getDecoder().decode(encryptedValue);
            byte[] decValue = c.doFinal(decordedValue);
            return new String(decValue);
        } catch (NoSuchAlgorithmException | InvalidKeyException | NoSuchPaddingException | BadPaddingException | IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return "";
    }
}