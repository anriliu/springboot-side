package com.github.yingzhuo.springboot.side.restful.security;

import java.io.Serializable;

public class AccessToken implements Serializable {

    private String username;
    private String password;

    public AccessToken() {
        super();
    }

    public AccessToken(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
