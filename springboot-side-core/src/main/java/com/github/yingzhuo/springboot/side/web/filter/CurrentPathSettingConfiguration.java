package com.github.yingzhuo.springboot.side.web.filter;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@ConditionalOnWebApplication
@ConditionalOnProperty(prefix = "springboot.side.currentpath-setting-filter", name = "enabled", havingValue = "true", matchIfMissing = true)
@EnableConfigurationProperties(CurrentPathSettingFilterProperties.class)
public class CurrentPathSettingConfiguration {

    @Bean
    @ConditionalOnMissingBean(CurrentPathSettingFilter.PathGenerator.class)
    public CurrentPathSettingFilter.PathGenerator pathGenerator() {
        return CurrentPathSettingFilter.PathGenerator.DEFAULT;
    }

    @Bean
    public FilterRegistrationBean currentPathSettingFilter(CurrentPathSettingFilterProperties properties, CurrentPathSettingFilter.PathGenerator pathGenerator) {
        CurrentPathSettingFilter filter = new CurrentPathSettingFilter();
        filter.setScope(properties.getScope());
        filter.setCurrentPathAttributeName(properties.getCurrentPathAttributeName());
        filter.setPathGenerator(pathGenerator);

        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setEnabled(properties.isEnabled());
        bean.addUrlPatterns(properties.getUrlPatterns());
        bean.setName(properties.getFilterName());
        bean.setOrder(properties.getFilterOrder());
        bean.setName(CurrentPathSettingFilter.class.getSimpleName());
        bean.setFilter(filter);
        return bean;
    }

}
