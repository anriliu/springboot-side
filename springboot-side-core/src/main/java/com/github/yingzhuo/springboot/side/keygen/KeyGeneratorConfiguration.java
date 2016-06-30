package com.github.yingzhuo.springboot.side.keygen;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;

public class KeyGeneratorConfiguration {

    @ConditionalOnMissingBean(KeyGenerator.class)
    @Bean(name = {"keyGenerator", "commonKeyGenerator", "keyGen", "kg"})
    public KeyGenerator keyGenerator() {
        return new CommonKeyGenerator();
    }

}
