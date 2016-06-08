package com.github.yingzhuo.springboot.side.restsec.core;

public final class RestsecHolder {

    private static final RestsecHolder INSTANCE = new RestsecHolder();

    public static RestsecHolder getInstance() {
        return INSTANCE;
    }

    private ThreadLocal<AccessToken> accessTokenThreadLocal = new ThreadLocal<>();
    private ThreadLocal<UserLike> userLikeThreadLocal = new ThreadLocal<>();

    private RestsecHolder() {
        super();
    }

    public AccessToken getAccessToken() {
        return accessTokenThreadLocal.get();
    }

    public UserLike getUserLike() {
        return userLikeThreadLocal.get();
    }

    void setAccessToken(AccessToken accessToken) {
        accessTokenThreadLocal.set(accessToken);
    }

    void setUserLike(UserLike userLike) {
        userLikeThreadLocal.set(userLike);
    }

}
