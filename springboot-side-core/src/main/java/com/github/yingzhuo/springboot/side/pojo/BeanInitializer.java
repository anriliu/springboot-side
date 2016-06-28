package com.github.yingzhuo.springboot.side.pojo;

import java.util.function.Function;

@FunctionalInterface
public interface BeanInitializer<T> extends Function<T, T> {

    public T apply(T pojo);

    default T initialize(T pojo) {
        return apply(checkPrecondition(pojo));
    }

    default T checkPrecondition(T pojo) {
        if (pojo == null) {
            throw new NullPointerException("Cannot initialize null value.");
        }
        return pojo;
    }

}
