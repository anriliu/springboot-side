package com.github.yingzhuo.springboot.commons.web.filter;

import com.github.yingzhuo.springboot.commons.logging.LoggerBeanImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@ConditionalOnProperty(prefix = "springboot.commons.request-logging-filter", name = "enabled", havingValue = "true", matchIfMissing = true)
@EnableConfigurationProperties(RequestLoggingFilterProperties.class)
public class RequestLoggingFilterConfiguration {

    @Autowired
    private RequestLoggingFilterProperties properties;


    @Bean
    public FilterRegistrationBean requestLoggingFilter() {

        RequestLoggingFilter filter = new RequestLoggingFilter();

        if (!properties.getExcludes().isEmpty()) {
            filter.clearExcludes();
            properties.getExcludes().forEach(filter::addExcludePattern);
        }

        filter.setLoggerBean(new LoggerBeanImpl(properties.getLoggerName(), properties.getLogLevel()));

        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(filter);
        bean.setEnabled(properties.isEnabled());
        bean.addUrlPatterns(properties.getUrlPattern());
        bean.setName(properties.getName());

        return bean;
    }
}
