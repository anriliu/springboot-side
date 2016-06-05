package com.github.yingzhuo.springboot.side.restful.security;


import com.github.yingzhuo.springboot.side.util.Base64Utils;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.NativeWebRequest;

public class BasicAuthenticationAccessTokenParser implements AccessTokenParser {

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String PREFIX = "Basic ";
    private static final int PREFIX_LEN = PREFIX.length();

    @Override
    public AccessToken parse(NativeWebRequest request) {
        String headerValue = request.getHeader(AUTHORIZATION_HEADER);

        if (!StringUtils.hasText(headerValue)) {
            return null;
        }

        if (!StringUtils.startsWithIgnoreCase(headerValue, PREFIX)) {
            return null;
        } else {
            headerValue = headerValue.substring(PREFIX_LEN);
        }

        String[] usernameAndPassword = Base64Utils.decode(headerValue).split(":");

        if (usernameAndPassword.length != 2) {
            return null;
        }

        return new AccessToken(usernameAndPassword[0], usernameAndPassword[1]);
    }

}
