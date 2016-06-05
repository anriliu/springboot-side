package com.github.yingzhuo.springboot.side.restful.security;

import java.io.Serializable;

public class UserDetailsHolder implements Serializable {

    private static final ThreadLocal<UserDetails> HOLDER = new ThreadLocal<>();
    private static final UserDetailsHolder INSTANCE = new UserDetailsHolder();

    public static UserDetailsHolder getInstance() {
        return INSTANCE;
    }

    private UserDetailsHolder() {
        super();
    }

    public UserDetails get() {
        return INSTANCE.get();
    }

    void set(UserDetails userDetails) {
        INSTANCE.set(userDetails);
    }
}
