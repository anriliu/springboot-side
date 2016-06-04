package com.github.yingzhuo.springboot.side.web.filter;

import com.github.yingzhuo.springboot.side.web.config.AbstractFilterProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.logging.LogLevel;
import org.springframework.core.Ordered;

import java.util.HashSet;
import java.util.Set;

@ConfigurationProperties(prefix = "springboot.side.request-logging-filter")
public class RequestLoggingFilterProperties extends AbstractFilterProperties {

    private Set<String> excludes = new HashSet<>();
    private String loggerName = RequestLoggingFilter.class.getName();
    private LogLevel logLevel = LogLevel.DEBUG;

    public RequestLoggingFilterProperties() {
        super.setEnabled(true);
        super.setFilterName(RequestLoggingFilter.class.getSimpleName());
        super.setFilterOrder(Ordered.LOWEST_PRECEDENCE);
        super.setUrlPatterns(new String[] {"/*"});

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

    public Set<String> getExcludes() {
        return excludes;
    }

    public void setExcludes(Set<String> excludes) {
        this.excludes = excludes;
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
