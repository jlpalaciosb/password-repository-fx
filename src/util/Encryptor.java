package util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class Encryptor {
    
    public static byte[] encrypt(String key, String value) {
        byte[] encrypted = null;
        try {
            String iv = "0101010101010101";
            SecretKeySpec _key = new SecretKeySpec(getBytes(key, 32), "AES");
            IvParameterSpec _iv = new IvParameterSpec(getBytes(iv, 16));

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, _key, _iv);

            encrypted = cipher.doFinal(value.getBytes("UTF-8"));
        } catch(Exception ex) {
            ex.printStackTrace(System.err);
        }
        return encrypted;
    }

    public static String decrypt(String key, byte[] encrypted) {
        String decrypted = null;
        try {
            String iv = "0101010101010101";
            SecretKeySpec _key = new SecretKeySpec(getBytes(key, 32), "AES");
            IvParameterSpec _iv = new IvParameterSpec(getBytes(iv, 16));

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, _key, _iv);

            decrypted = new String(cipher.doFinal(encrypted), "UTF-8");
        } catch(Exception ex) {
            ex.printStackTrace(System.err);
        }
        return decrypted;
    }
    
    public static byte[] sha256(String str) {
        byte[] hash = null;
        try {
            MessageDigest digester = MessageDigest.getInstance("SHA-256");
            hash = digester.digest(str.getBytes("UTF-8"));
        } catch(UnsupportedEncodingException | NoSuchAlgorithmException ex) {
            ex.printStackTrace(System.err);
        }
        return hash;
    }
    
    /* Generate a byte array of length equal to n from the received string */
    private static byte[] getBytes(String str, int n) {
        byte[] result = new byte[n];
        try {
            byte[] str_bytes = str.getBytes("UTF-8");
            for(int i = 0; i < n; i++)
                result[i] = (i < str_bytes.length) ? str_bytes[i] : 0;
        } catch(UnsupportedEncodingException ex) {
            ex.printStackTrace(System.err);
        }
        return result;
    }
    
    public static boolean equal(byte[] arr1, byte[] arr2) {
        if(arr1.length != arr2.length) return false;
        
        int l = arr1.length;
        for(int i = 0; i < l; i++)
            if(arr1[i] != arr2[i])
                return false;
        
        return true;
    }
    
}
