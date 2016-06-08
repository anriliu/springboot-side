package com.github.yingzhuo.springboot.side.restsec.core;

@FunctionalInterface
public interface UserLikeLoader {

    public UserLike load(AccessToken accessToken);

}
