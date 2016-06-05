package com.github.yingzhuo.springboot.side.restful.security;

import org.springframework.web.context.request.NativeWebRequest;

@FunctionalInterface
public interface AccessTokenParser {

    public AccessToken parse(NativeWebRequest request);

}
