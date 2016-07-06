package com.github.yingzhuo.springboot.side.encoder;

import org.apache.commons.codec.digest.DigestUtils;

public class Sha1Encoder implements Encoder {

    @Override
    public String encode(CharSequence rawText) {
        return DigestUtils.sha1Hex(rawText.toString());
    }

}
