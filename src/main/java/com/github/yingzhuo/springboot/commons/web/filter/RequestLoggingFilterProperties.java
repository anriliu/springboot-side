package com.github.yingzhuo.springboot.commons.web.filter;

import com.github.yingzhuo.springboot.commons.logging.LogLevel;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@ConfigurationProperties(prefix = "springboot.commons.request-logging-filter")
public class RequestLoggingFilterProperties implements Serializable {

    private boolean enabled = true;
    private Set<String> excludes = new HashSet<>();
    private String urlPattern;
    private String name = RequestLoggingFilter.class.getSimpleName();
    private String loggerName = RequestLoggingFilter.class.getName();
    private LogLevel logLevel = LogLevel.DEBUG;

    public RequestLoggingFilterProperties() {
        excludes.add("/**/*.ico");
        excludes.add("/**/*.js");
        excludes.add("/**/*.css");
        excludes.add("/**/*.gif");
        excludes.add("/**/*.png");
        excludes.add("/**/*.bmp");
        excludes.add("/**/*.jpg");
        excludes.add("/**/*.ico");
        excludes.add("/*.ico");
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Set<String> getExcludes() {
        return excludes.parallelStream().map(String::trim).collect(Collectors.toSet());
    }

    public void setExcludes(Set<String> excludes) {
        this.excludes = excludes;
    }

    public String getUrlPattern() {
        return urlPattern;
    }

    public void setUrlPattern(String urlPattern) {
        this.urlPattern = urlPattern;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLoggerName() {
        return loggerName;
    }

    public void setLoggerName(String loggerName) {
        this.loggerName = loggerName;
    }

    public LogLevel getLogLevel() {
        return logLevel;
    }

    public void setLogLevel(LogLevel logLevel) {
        this.logLevel = logLevel;
    }
}
