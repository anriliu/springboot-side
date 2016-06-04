package com.github.yingzhuo.springboot.side.web.filter;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

@EnableConfigurationProperties(HttpContextHoldingProperties.class)
@ConditionalOnProperty(prefix = "springboot.side.http-context-holding-filter", name = "enabled", havingValue = "true", matchIfMissing = true)
public class HttpContextHoldingConfiguration extends WebMvcConfigurerAdapter {

    @Bean
    public FilterRegistrationBean httpContextHoldingFilter(HttpContextHoldingProperties properties) {
        HttpContextHoldingFilter filter = new HttpContextHoldingFilter();

        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setEnabled(properties.isEnabled());
        bean.setFilter(filter);
        bean.setName(properties.getFilterName());
        bean.setOrder(properties.getFilterOrder());
        bean.addUrlPatterns(properties.getUrlPatterns());

        return bean;
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new HttpContextHolderHandlerMethodArgumentResolver());
    }

}
