package com.github.yingzhuo.springboot.side.util;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

@ConditionalOnMissingBean(SpringUtils.class)
public class SpringUtilsConfiguration {

    @Bean(name = "springUtils")
    public SpringUtils springUtils() {
        return new SpringUtils();
    }

}
