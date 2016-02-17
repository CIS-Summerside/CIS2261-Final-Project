package api.tools;

import java.util.Random;

/**
 * File: ${FILE}
 * Author: compa
 * Created: 2016-02-16
 */
public class UrlTools {
    public static String getBase64(String string) {
        String sampleAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        char[] buf = new char[12];
        for (int i = 0; i < 12; i++)
            buf[i] = sampleAlphabet.charAt(random.nextInt(sampleAlphabet.length()));
        return String.valueOf(buf);
    }
}
