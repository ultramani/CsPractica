package com.example.myfirstjob.data;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class TypePassword {
    /**
     * Clase estática que se encarga de encriptar las contraseñas
     *
     * @param password
     * @return String de la contraseña introducida encriptada
     */
    public static String encryptPassword(String password) {
        try {
            String init = "wzY5af}<^np|9f$A";
            String secret_key = "*%)&!}VNen%ovPE<";
            SecretKeySpec skeySpec = new SecretKeySpec(secret_key.getBytes("UTF-8"), "AES");
            IvParameterSpec iv = new IvParameterSpec(init.getBytes("UTF-8"));

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

            byte[] encrypted = cipher.doFinal(password.getBytes());
            return Base64.getEncoder().encodeToString(encrypted);
        } catch (Exception ex) {
        }
        return null;
    }
}
