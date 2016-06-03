package com.github.yingzhuo.springboot.commons.util;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

@ConditionalOnMissingBean(SpringUtils.class)
public class SpringUtilsConfiguration {

    @Bean(name = "__spring_utils__")
    public SpringUtils springUtils() {
        return new SpringUtils();
    }

}
