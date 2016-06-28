package com.github.yingzhuo.springboot.side.util;

import org.springframework.context.annotation.Bean;

public class UtilsConfiguration {

    @Bean(name = "___spring_utils___")
    public SpringUtils springUtils() {
        return SpringUtils.INSTANCE;
    }

}
