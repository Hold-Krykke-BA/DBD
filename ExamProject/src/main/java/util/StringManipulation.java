package util;

import java.util.Random;

public class StringManipulation {
    static final String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    public static String generateRandomString(int targetStringLength) {
        Random random = new Random();
        StringBuilder strB = new StringBuilder(targetStringLength);
            for(int i = 0; i < targetStringLength; i++)
                strB.append(ALPHABET.charAt(random.nextInt(ALPHABET.length())));
            return strB.toString();
    }
}
