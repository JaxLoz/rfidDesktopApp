package org.wrs.util;

import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author C.Mateo
 */
public class PasswordUtil {

    public static boolean verifyPassword(String plainTextPassword, String hashedPassword) {
        return BCrypt.checkpw(plainTextPassword, hashedPassword);
    }
    
}
