package com.github.yingzhuo.springboot.commons.web.filter;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@EnableConfigurationProperties(HttpContextHoldingProperties.class)
@ConditionalOnProperty(prefix = "springboot.commons.http-context-holding-filter", name = "enabled", havingValue = "true", matchIfMissing = true)
public class HttpContextHoldingConfiguration {


    @Bean
    public FilterRegistrationBean httpContextHoldingFilter(HttpContextHoldingProperties properties) {
        HttpContextHoldingFilter filter = new HttpContextHoldingFilter();

        System.out.println(properties.isEnabled());
        System.out.println(properties.getFilterName());
        System.out.println(properties.getFilterOrder());
        System.out.println(properties.getUrlPatterns());

        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setEnabled(properties.isEnabled());
        bean.setFilter(filter);
        bean.setName(properties.getFilterName());
        bean.setOrder(properties.getFilterOrder());
        bean.addUrlPatterns(properties.getUrlPatterns().toArray(new String[properties.getUrlPatterns().size()]));

        return bean;
    }
}
