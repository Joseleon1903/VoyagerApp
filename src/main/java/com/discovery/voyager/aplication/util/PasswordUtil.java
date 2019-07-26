package com.discovery.voyager.aplication.util;

import java.util.Random;

/**
* jose de leon
*/
public class PasswordUtil {

    public static final String numbers = "123456789";
    public static final String alphabetLower = "abcdefghijklmnopqrstuvwxyz";
    public static final String alphabetUpper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static String generatePassword(int len) {

        // Using random method
        Random rndm_method = new Random();

        StringBuilder password = new StringBuilder();

        for (int i = 0; i < 3; i++) {
            password.append(alphabetLower.charAt(rndm_method.nextInt(alphabetLower.length())));
        }

        for (int i = 0; i < 3; i++) {
            password.append(numbers.charAt(rndm_method.nextInt(numbers.length())));
        }

        for (int i = 0; i < 2; i++) {
            password.append(alphabetUpper.charAt(rndm_method.nextInt(alphabetUpper.length())));
        }
        return password.toString();
    }
}
