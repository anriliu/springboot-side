package com.github.yingzhuo.springboot.side.restsec.impl;

import com.github.yingzhuo.springboot.side.restsec.core.AccessToken;
import com.github.yingzhuo.springboot.side.restsec.core.UserLike;
import com.github.yingzhuo.springboot.side.restsec.core.UserLikeLoader;

public class NullReturningUserLikeLoader implements UserLikeLoader {

    @Override
    public UserLike load(AccessToken accessToken) {
        return null;
    }

}
