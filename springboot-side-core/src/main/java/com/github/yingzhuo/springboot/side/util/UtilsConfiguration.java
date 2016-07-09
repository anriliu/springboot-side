package com.github.yingzhuo.springboot.side.util;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

public class UtilsConfiguration {

    @ConditionalOnMissingBean(SpringUtils.class)
    @Bean(name = "___spring_utils___")
    public SpringUtils springBeanUtils() {
        return SpringUtils.INSTANCE;
    }

}
