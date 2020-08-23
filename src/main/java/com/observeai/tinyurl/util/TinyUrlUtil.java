package com.observeai.tinyurl.util;

public class TinyUrlUtil {
    private static String code = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    public static String getTinyUrl(long l) {
        return decTo62(l);
    }

    private static String decTo62(long l) {
        StringBuffer result = new StringBuffer();
        while (l > 0) {
            long m = l / 62;
            long n = l - m * 62;
            result.append(code.charAt((int)n));
            l = m;
        }
        return result.toString();
    }
}
