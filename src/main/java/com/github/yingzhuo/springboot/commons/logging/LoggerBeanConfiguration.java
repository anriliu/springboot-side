package com.github.yingzhuo.springboot.commons.logging;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@ConditionalOnClass(Logger.class)
@ConditionalOnMissingBean(LoggerBean.class)
@ConditionalOnProperty(prefix = "springboot.side.logger-bean", name = "enabled", havingValue = "true", matchIfMissing = true)
@EnableConfigurationProperties(LoggerBeanProperties.class)
public class LoggerBeanConfiguration {

    @Autowired
    private LoggerBeanProperties properties;

    @Bean
    public LoggerBean loggerBean() {
        return new LoggerBeanImpl(properties.getName(), properties.getDefaultLevel());
    }

}
