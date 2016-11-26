package com.github.yingzhuo.springboot.side.pojo;

import java.util.Objects;
import java.util.function.Function;

@FunctionalInterface
public interface BeanInitializer<T> extends Function<T, T> {

    public T apply(T pojo);

    default T initialize(T pojo) {
        return apply(checkPrecondition(pojo));
    }

    default T checkPrecondition(T pojo) {
        return Objects.requireNonNull(pojo, "Cannot initialize null value.");
    }

}
