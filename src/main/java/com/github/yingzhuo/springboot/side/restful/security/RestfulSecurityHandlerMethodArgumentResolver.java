package com.github.yingzhuo.springboot.side.restful.security;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class RestfulSecurityHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType() == AccessToken.class || parameter.getParameterType() == UserDetails.class;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        if (parameter.getParameterType() == AccessToken.class) {
            return AccessTokenHolder.getInstance().get();
        }

        if (parameter.getParameterType() == UserDetails.class) {
            return UserDetailsHolder.getInstance().get();
        }

        return null;
    }
}
