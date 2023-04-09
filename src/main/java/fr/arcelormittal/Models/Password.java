package fr.arcelormittal.Models;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;

public class Password {

    private static final int LENGHT = 18;
    private static final String CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnoprstuvwxyz1234567890";

    public static String getRandomPassword(){
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i <= LENGHT; i++) {
            int randomIndex = random.nextInt(CHARS.length());
            sb.append(CHARS.charAt(randomIndex));
        }

        return sb.toString();
    }

    public static String getRandomPassword(int lenght){
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i <= lenght; i++) {
            int randomIndex = random.nextInt(CHARS.length());
            sb.append(CHARS.charAt(randomIndex));
        }

        return sb.toString();
    }

    public static String doHashing(String password) {
        StringBuilder sb = new StringBuilder();
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(password.getBytes());
            byte[] resultByteArray = messageDigest.digest();

            for (Byte b : resultByteArray) {
                sb.append(String.format("%02x",b));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}