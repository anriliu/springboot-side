package com.github.yingzhuo.springboot.side.restsec.web;

import com.github.yingzhuo.springboot.side.restsec.core.AccessToken;
import com.github.yingzhuo.springboot.side.restsec.core.RestsecHolder;
import com.github.yingzhuo.springboot.side.restsec.core.UserLike;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class RestsecHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        boolean con1 = parameter.getParameterType() == UserLike.class;
        boolean con2 = parameter.getParameterType() == AccessToken.class;
        return con1 || con2;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        if (parameter.getParameterType() == UserLike.class) return RestsecHolder.getInstance().getUserLike();
        if (parameter.getParameterType() == AccessToken.class) return RestsecHolder.getInstance().getAccessToken();
        return null;
    }

}
