package com.getmarco.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Simple fake user authentication service. Allows users to be authenticated
 * against a hard-coded internal list of usernames and passwords.
 */
public class LoginValidation {

    private static final Map<String, String> users = new HashMap<>();
    static {
        users.put("user", "secret");
        users.put("admin", "admin");
    }

    /**
     * Compares provided username and password against an internal list of
     * credentials. Immediately returns false if either username or password
     * is null or contains no non-whitespace characters.
     *
     * @param username
     * @param password
     * @return true if matching username/password pair found in internal list, else false
     */
    public static boolean authenticate(String username, String password) {
        if (!hasText(username) || !hasText(password))
            return false;

        String expectedPassword = users.get(username);
        return expectedPassword != null && expectedPassword.equals(password);
    }

    /**
     * Checks if provided string has any non-whitespace characters. Returns
     * false if given string is null.
     *
     * @param s
     * @return  true if string contains non-whitespace characters, else false
     */
    public static boolean hasText(String s) {
        return s != null && s.trim().length() != 0;
    }
}
