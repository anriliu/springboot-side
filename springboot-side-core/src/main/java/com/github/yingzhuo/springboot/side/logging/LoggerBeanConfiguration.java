package com.github.yingzhuo.springboot.side.logging;

import org.slf4j.Logger;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.logging.LogLevel;
import org.springframework.context.annotation.Bean;

import java.io.Serializable;

@ConditionalOnClass(Logger.class)
@ConditionalOnMissingBean(LoggerBean.class)
@ConditionalOnProperty(prefix = "springboot.side.logger-bean", name = "enabled", havingValue = "true", matchIfMissing = true)
@EnableConfigurationProperties(LoggerBeanConfiguration.LoggerBeanProperties.class)
public class LoggerBeanConfiguration {

    @Bean
    public LoggerBean loggerBean(LoggerBeanProperties properties) {
        return new LoggerBeanImpl(properties.getName(), properties.getDefaultLevel());
    }

    @ConfigurationProperties(prefix = "springboot.side.logger-bean")
    public static class LoggerBeanProperties implements Serializable {
        private boolean enabled = true;
        private String name = LoggerBean.class.getName();
        private LogLevel defaultLevel = LogLevel.DEBUG;

        public boolean isEnabled() {
            return enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public LogLevel getDefaultLevel() {
            return defaultLevel;
        }

        public void setDefaultLevel(LogLevel defaultLevel) {
            this.defaultLevel = defaultLevel;
        }
    }

}
