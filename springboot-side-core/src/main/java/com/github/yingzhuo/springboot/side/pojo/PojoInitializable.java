package com.github.yingzhuo.springboot.side.pojo;

import java.util.function.Function;

@FunctionalInterface
public interface PojoInitializable<T> extends Function<T, T> {

    // public T apply(T pojo);

    public default T initialize(T pojo) {
        return apply(precondition(pojo));
    }

    public default T precondition(T pojo) {
        if (pojo == null) {
            throw new NullPointerException("Cannot initialize null value.");
        }
        return pojo;
    }

}
