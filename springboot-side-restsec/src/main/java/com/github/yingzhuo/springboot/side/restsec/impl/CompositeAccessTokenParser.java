package com.github.yingzhuo.springboot.side.restsec.impl;

import com.github.yingzhuo.springboot.side.restsec.core.AccessToken;
import com.github.yingzhuo.springboot.side.restsec.core.AccessTokenParser;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.List;

public class CompositeAccessTokenParser implements AccessTokenParser, InitializingBean {

    private List<AccessTokenParser> accessTokenParsers;

    public CompositeAccessTokenParser() {
        super();
    }

    public CompositeAccessTokenParser(List<AccessTokenParser> accessTokenParsers) {
        super();
        this.accessTokenParsers = accessTokenParsers;
    }

    @Override
    public AccessToken parse(NativeWebRequest nativeWebRequest) {
        for (AccessTokenParser parser : getAccessTokenParsers()) {
            AccessToken accessToken = parser.parse(nativeWebRequest);
            if (accessToken != null) {
                return accessToken;
            }
        }
        return null;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(accessTokenParsers);
    }

    public Iterable<AccessTokenParser> getAccessTokenParsers() {
        return accessTokenParsers;
    }

    public void setAccessTokenParsers(List<AccessTokenParser> accessTokenParsers) {
        this.accessTokenParsers = accessTokenParsers;
    }

}
