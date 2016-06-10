package com.github.yingzhuo.springboot.side.restsec.event;

import com.github.yingzhuo.springboot.side.restsec.core.AccessToken;
import com.github.yingzhuo.springboot.side.restsec.core.UserLike;
import org.springframework.web.context.request.NativeWebRequest;

public interface RestsecEventListener {

    public static RestsecEventListener DEFAULT = new RestsecEventListenerAdapter() {
    };

    public void beforeAccessTokenParsing(NativeWebRequest nativeWebRequest);

    public void afterAccessTokenParsing(NativeWebRequest nativeWebRequest, AccessToken accessToken);

    public void beforeUserLikeLoading(AccessToken accessToken);

    public void afterUserLikeLoading(AccessToken accessToken, UserLike userLike);

}
