package com.github.yingzhuo.springboot.side.encoder;

import org.apache.commons.codec.digest.DigestUtils;

public class Md5Encoder implements Encoder {

    @Override
    public String encode(CharSequence rawText) {
        return DigestUtils.md5Hex(rawText.toString());
    }

}
