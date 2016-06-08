package com.github.yingzhuo.springboot.side.restsec.impl;

import com.github.yingzhuo.springboot.side.restsec.core.StringAccessToken;

public class SimpleStringAccessToken implements StringAccessToken {

    private final String token;

    public SimpleStringAccessToken(String token) {
        this.token = token;
    }

    @Override
    public String getToken() {
        return this.token;
    }

    @Override
    public String toString() {
        return getToken();
    }

}
