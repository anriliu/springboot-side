package com.github.yingzhuo.springboot.side.restful.security;

public class AccessTokenHolder {

    private static final AccessTokenHolder INSTANCE = new AccessTokenHolder();

    private static final ThreadLocal<AccessToken> HOLDER = new ThreadLocal<>();

    public static AccessTokenHolder getInstance() {
        return INSTANCE;
    }

    private AccessTokenHolder() {
        super();
    }

    public AccessToken get() {
        return HOLDER.get();
    }

    void set(AccessToken accessToken) {
        HOLDER.set(accessToken);
    }

}
