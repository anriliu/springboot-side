package com.github.yingzhuo.springboot.commons.logging;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.Serializable;

@ConfigurationProperties(prefix = "springboot.side.logger-bean")
public class LoggerBeanProperties implements Serializable {

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
