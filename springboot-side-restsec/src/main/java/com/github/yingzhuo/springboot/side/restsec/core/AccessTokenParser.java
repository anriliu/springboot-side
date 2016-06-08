package com.github.yingzhuo.springboot.side.restsec.core;

import org.springframework.web.context.request.NativeWebRequest;

@FunctionalInterface
public interface AccessTokenParser {

    public AccessToken parse(NativeWebRequest nativeWebRequest);

}
