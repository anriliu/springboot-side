package com.github.yingzhuo.springboot.side.restsec.impl;

import com.github.yingzhuo.springboot.side.restsec.core.AccessToken;
import com.github.yingzhuo.springboot.side.restsec.core.AccessTokenParser;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.NativeWebRequest;

public abstract class AbstractAuthorizationHeaderTokenParser implements AccessTokenParser {

    private static final String AUTHORIZATION_HEADER = "Authorization";

    @Override
    public final AccessToken parse(NativeWebRequest webRequest) {
        String headerValue = webRequest.getHeader(AUTHORIZATION_HEADER);

        if (!StringUtils.hasText(headerValue)) {
            return null;
        }

        return doParse(headerValue);
    }

    protected abstract AccessToken doParse(String headerValue);
}
