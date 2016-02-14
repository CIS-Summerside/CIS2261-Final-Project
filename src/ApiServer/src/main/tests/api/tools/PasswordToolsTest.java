package api.tools;

import org.junit.Assert;

/**
 * Created by connor on 2/9/16.
 */
public class PasswordToolsTest {

    @org.junit.Test
    public void testGenerateSalt() throws Exception {
        final Integer maxSize = 24;
        String generatedSalt = api.tools.PasswordTools.generateSalt();

        assert generatedSalt.length() == maxSize;
    }

    @org.junit.Test
    public void testSha256Hash() throws Exception {
        Integer maxLength = 64;
        String testStr = "Hello, world!";
        String knownGoodHash = "315f5bdb76d078c43b8ac0064e4a0164612b1fce77c869345bfc94c75894edd3";
        String generatedHash = api.tools.PasswordTools.sha256Hash(testStr);

        //Assert the hash we generate is the same as our known good value
        assert knownGoodHash.equals(generatedHash);

        //Assert that the generated hash is the required length
        assert generatedHash.length() == maxLength;
        
    }
}