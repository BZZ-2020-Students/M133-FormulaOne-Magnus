package ch.formula.one.util;

import ch.formula.one.service.Config;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.KeySpec;
import java.util.Base64;

/**
 * @author Lokesh Gupta(<a href="https://howtodoinjava.com/java/java-security/aes-256-encryption-decryption/">https://howtodoinjava.com/java/java-security/aes-256-encryption-decryption/</a>)   *
 */
public class AES256 {
    private static String SECRET_KEY;
    private static String SALT;


    private static void getKey(){
        SECRET_KEY = Config.getProperty("SECRET_KEY");
        SALT = Config.getProperty("SALT");
    }

    private interface Executable {
        String execute(IvParameterSpec ivSpec, SecretKeyFactory factory, KeySpec spec, SecretKey tmp, SecretKeySpec secretKey) throws IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException, InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException;
    }

    private static String execute(Executable exec) {
        getKey();
        try {
            byte[] iv = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            IvParameterSpec ivspec = new IvParameterSpec(iv);

            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            KeySpec spec = new PBEKeySpec(SECRET_KEY.toCharArray(), SALT.getBytes(), 65536, 256);
            SecretKey tmp = factory.generateSecret(spec);
            SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");

            return exec.execute(ivspec, factory, spec, tmp, secretKey);

        } catch (Exception e) {
            System.out.println("Error while enc/decrypting: " + e);
        }

        return null;
    }

    public static String encrypt(String strToEncrypt) {
        getKey();
        return execute(
                (ivSpec, factory, spec, tmp, secretKey) -> {
                    Cipher cipher;
                    cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
                    cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivSpec);
                    return Base64.getEncoder()
                            .encodeToString(cipher.doFinal(strToEncrypt.getBytes(StandardCharsets.UTF_8)));
                });
    }

    public static String decrypt(String strToDecrypt) {
        getKey();
        return execute(
                (ivSpec, factory, spec, tmp, secretKey) -> {
                    Cipher cipher;
                    try {
                        cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
                    } catch (NoSuchAlgorithmException |
                             NoSuchPaddingException e) {
                        throw new RuntimeException(e);
                    }
                    cipher.init(Cipher.DECRYPT_MODE, secretKey, ivSpec);
                    return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
                });
    }
}
