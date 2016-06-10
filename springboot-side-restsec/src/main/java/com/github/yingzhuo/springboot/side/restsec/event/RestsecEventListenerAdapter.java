package com.github.yingzhuo.springboot.side.restsec.event;

import com.github.yingzhuo.springboot.side.restsec.core.AccessToken;
import com.github.yingzhuo.springboot.side.restsec.core.UserLike;
import org.springframework.web.context.request.NativeWebRequest;

public abstract class RestsecEventListenerAdapter implements RestsecEventListener {

    @Override
    public void beforeAccessTokenParsing(NativeWebRequest nativeWebRequest) {
    }

    @Override
    public void afterAccessTokenParsing(NativeWebRequest nativeWebRequest, AccessToken accessToken) {
    }

    @Override
    public void beforeUserLikeLoading(AccessToken accessToken) {
    }

    @Override
    public void afterUserLikeLoading(AccessToken accessToken, UserLike userLike) {
    }
}
