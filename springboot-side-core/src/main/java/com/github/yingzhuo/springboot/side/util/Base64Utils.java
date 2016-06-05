package com.github.yingzhuo.springboot.side.util;

import java.nio.charset.Charset;
import java.util.Base64;

public class Base64Utils {

    private static final Charset UTF_8 = Charset.forName("UTF-8");

    public static String encode(String plainText) {
        return new String(Base64.getUrlEncoder().encode(plainText.getBytes(UTF_8)));
    }

    public static String decode(String encodeString) {
        return new String(Base64.getUrlDecoder().decode(encodeString));
    }
}
