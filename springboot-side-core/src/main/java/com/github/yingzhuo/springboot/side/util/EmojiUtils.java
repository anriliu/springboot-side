package com.github.yingzhuo.springboot.side.util;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

public class EmojiUtils {

    private EmojiUtils() {
    }

    public static String removeEmojis(String text) {
        return removeEmojis(text, "UTF-8");
    }

    public static String removeEmojis(String text, String encoding) {
        if (text == null) {
            return null;
        }

        try {
            byte[] bytes = text.getBytes(encoding);
            ByteBuffer buffer = ByteBuffer.allocate(bytes.length);
            int i = 0;
            while (i < bytes.length) {
                short b = bytes[i];
                if (b > 0) {
                    buffer.put(bytes[i++]);
                    continue;
                }

                b += 256; // 去掉符号位

                if (((b >> 5) ^ 0x6) == 0) {
                    buffer.put(bytes, i, 2);
                    i += 2;
                } else if (((b >> 4) ^ 0xE) == 0) {
                    buffer.put(bytes, i, 3);
                    i += 3;
                } else if (((b >> 3) ^ 0x1E) == 0) {
                    i += 4;
                } else if (((b >> 2) ^ 0x3E) == 0) {
                    i += 5;
                } else if (((b >> 1) ^ 0x7E) == 0) {
                    i += 6;
                } else {
                    buffer.put(bytes[i++]);
                }
            }
            buffer.flip();
            return new String(buffer.array(), encoding);
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
