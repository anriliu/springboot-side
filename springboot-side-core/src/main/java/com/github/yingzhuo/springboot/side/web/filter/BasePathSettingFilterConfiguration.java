package com.github.yingzhuo.springboot.side.web.filter;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@ConditionalOnWebApplication
@ConditionalOnProperty(prefix = "springboot.side.basepath-setting-filter", name = "enabled", havingValue = "true", matchIfMissing = true)
@EnableConfigurationProperties(BasePathSettingFilterProperties.class)
public class BasePathSettingFilterConfiguration {

    @Bean
    public FilterRegistrationBean requestLoggingFilter(BasePathSettingFilterProperties properties) {
        BasePathSettingFilter filter = new BasePathSettingFilter();
        filter.setBasepathAttributeNames(properties.getBasepathAttributeNames());
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(filter);
        bean.setEnabled(properties.isEnabled());
        bean.addUrlPatterns(properties.getUrlPatterns());
        bean.setName(properties.getFilterName());
        bean.setOrder(properties.getFilterOrder());
        return bean;
    }
}
