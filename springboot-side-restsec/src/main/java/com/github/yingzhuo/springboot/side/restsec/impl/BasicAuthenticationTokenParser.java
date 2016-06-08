package com.github.yingzhuo.springboot.side.restsec.impl;

import com.github.yingzhuo.springboot.side.restsec.core.AccessToken;
import com.github.yingzhuo.springboot.side.util.Base64Utils;
import org.springframework.util.StringUtils;

public class BasicAuthenticationTokenParser extends AbstractAuthorizationHeaderTokenParser {

    private static final String PREFIX = "Basic ";
    private static final int PREFIX_LEN = PREFIX.length();

    @Override
    protected AccessToken doParse(String headerValue) {

        if (!StringUtils.startsWithIgnoreCase(headerValue, PREFIX)) {
            return null;
        } else {
            headerValue = headerValue.substring(PREFIX_LEN);
        }

        String[] usernameAndPassword = Base64Utils.decode(headerValue).split(":");

        if (usernameAndPassword.length != 2) {
            return null;
        }

        return new SimpleUsernamePasswordAccessToken(usernameAndPassword[0], usernameAndPassword[1]);
    }
}
