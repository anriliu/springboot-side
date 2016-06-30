package com.github.yingzhuo.springboot.side.keygen;

import org.springframework.cache.interceptor.KeyGenerator;

import java.lang.reflect.Method;

public class CommonKeyGenerator implements KeyGenerator {

    private static final String NULL = "<null>";

    @Override
    public Object generate(Object target, Method method, Object... params) {
        StringBuilder sb = new StringBuilder();
        sb.append(target.getClass().getName());
        sb.append(method.getName());
        for (Object obj : params) {
            sb.append(obj.toString() != null ? obj.toString() : NULL);
        }
        return sb.toString();
    }

}
