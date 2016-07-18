package com.github.yingzhuo.springboot.side.web.filter;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.context.SecurityContextHolder;

@ConditionalOnClass(SecurityContextHolder.class)
@ConditionalOnProperty(prefix = "springboot.side.security-context-setting-filter", name = "enabled", havingValue = "true", matchIfMissing = true)
@EnableConfigurationProperties(SecurityContextSettingFilterProperties.class)
public class SecurityContextSettingFilterConfiguration {

    @Bean
    public FilterRegistrationBean securityContextSettingFilter(SecurityContextSettingFilterProperties properties) {
        FilterRegistrationBean bean = new FilterRegistrationBean();

        SecurityContextSettingFilter filter = new SecurityContextSettingFilter();
        filter.setScope(properties.getScope());
        filter.setUserDetailsAttributeName(properties.getUserDetailsAttributeName());
        filter.setUserDetailsUsernameAttributeName(properties.getUserDetailsUsernameAttributeName());
        filter.setUserDetailsAuthoritiesAttributeName(properties.getUserDetailsAuthoritiesAttributeName());

        bean.setFilter(filter);
        bean.setEnabled(properties.isEnabled());
        bean.addUrlPatterns(properties.getUrlPatterns());
        bean.setName(properties.getFilterName());
        bean.setOrder(properties.getFilterOrder());
        bean.setName(SecurityContextSettingFilter.class.getSimpleName());

        return bean;
    }

}
