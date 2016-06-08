package com.github.yingzhuo.springboot.side.restsec.impl;

import com.github.yingzhuo.springboot.side.restsec.core.UsernamePasswordAccessToken;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class SimpleUsernamePasswordAccessToken implements UsernamePasswordAccessToken {

    private final String username;
    private final String password;

    public SimpleUsernamePasswordAccessToken(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

}
