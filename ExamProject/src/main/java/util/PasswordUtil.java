package util;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtil {

    /**
     * Hashes a password
     *
     * @param password the plaintext password
     * @return the hashed password
     */
    public static String hashpw(String password) {
        // gensalt's log_rounds parameter determines the complexity
        // the work factor is 2**log_rounds, and the default is 10
        //pass integer parameter to change from default
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    /**
     * Checks if a plaintext password matches the hashed one
     *
     * @param candidate The plaintext password
     * @param hashed    The hashtext password
     * @return True if match, else false.
     */
    public static boolean checkpw(String candidate, String hashed) {
        return BCrypt.checkpw(candidate, hashed);
    }


}
