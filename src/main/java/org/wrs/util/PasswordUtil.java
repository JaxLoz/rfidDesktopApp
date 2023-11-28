package org.wrs.util;

import org.mindrot.jbcrypt.BCrypt;

/**
 * Utility class for working with passwords.
 * 
 * @author C.Mateo
 */
public class PasswordUtil {

    /**
     * Verifies if a plain text password matches a hashed password.
     * 
     * @param plainTextPassword The plain text password to verify.
     * @param hashedPassword The hashed password to compare against.
     * @return True if the passwords match, false otherwise.
     */
    public static boolean verifyPassword(String plainTextPassword, String hashedPassword) {
        return BCrypt.checkpw(plainTextPassword, hashedPassword);
    }

    /**
     * Hashes a plain text password using BCrypt.
     * 
     * @param plainTextPassword The plain text password to hash.
     * @return The hashed password.
     */
    public static String hashPassword(String plainTextPassword) {
        // Gensalt's log_rounds parameter determines the complexity
        // The higher the number, the more work factor is needed (exponential)
        int logRounds = 10; // You can adjust this value according to your needs
        return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt(logRounds));
    }
}
