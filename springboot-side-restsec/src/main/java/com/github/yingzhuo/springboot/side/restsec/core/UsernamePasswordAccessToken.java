package com.github.yingzhuo.springboot.side.restsec.core;

public interface UsernamePasswordAccessToken extends AccessToken {

    public String getUsername();

    public String getPassword();

}
