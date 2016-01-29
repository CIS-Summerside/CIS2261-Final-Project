package api.tools;

import org.apache.tomcat.util.codec.binary.Base64;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

/**
 * Created by compa on 2016-01-29.
 */
public class PasswordTools {
    public static String generateSalt(){
        final Random r = new SecureRandom();
        byte[] salt = new byte[16];
        r.nextBytes(salt);
        return Base64.encodeBase64String(salt);
    }

    public static String saltedPassHash(String passwordHash, String passwordSalt) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        String saltPass = passwordSalt + passwordHash;
        md.update(saltPass.getBytes("UTF-8"));
        byte[] digest = md.digest();
        return Base64.encodeBase64String(digest);
    }
}
