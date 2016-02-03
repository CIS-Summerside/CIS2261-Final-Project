package api.tools;

import org.apache.tomcat.util.codec.binary.Base64;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

/**
 * File: ${FILE}
 * Author: compa
 * Created: 2016-01-29
 */
public class PasswordTools {

    /**
     * Returns a 24 character long string of a randomly generated
     * value to be used for salting a users password.
     *
     * @return      24 character long randomly generated salt value
     */
    public static String generateSalt(){
        Random r = new SecureRandom();
        byte[] salt = new byte[16];
        r.nextBytes(salt);
        return Base64.encodeBase64String(salt);
    }

    /**
     * Returns the SHA-256 hash of a users salt and password. The password
     * provided should have already been hashed. Use this to compare users
     * info in DB to entered user login data.
     *
     * @param  passwordHash  User's hashed password
     * @param  passwordSalt  Salt for the user's password
     * @return      SHA-256 hash of Password Salt + Password Hash
     */
    public static String sha256Hash(String strToHash) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(strToHash.getBytes("UTF-8"));
        byte[] digest = md.digest();
        return Base64.encodeBase64String(digest);
    }
}
