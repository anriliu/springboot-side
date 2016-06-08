package com.github.yingzhuo.springboot.side.restsec.impl;

import com.github.yingzhuo.springboot.side.restsec.core.AccessToken;
import org.springframework.util.StringUtils;

public class BearerAuthorizationTokenParser extends AbstractAuthorizationHeaderTokenParser {

    private static final String PREFIX = "Bearer ";
    private static final int PREFIX_LEN = PREFIX.length();

    @Override
    protected AccessToken doParse(String headerValue) {
        if (!StringUtils.startsWithIgnoreCase(headerValue, PREFIX)) {
            return null;
        } else {
            headerValue = headerValue.substring(PREFIX_LEN);
        }

        return null;
    }

}
