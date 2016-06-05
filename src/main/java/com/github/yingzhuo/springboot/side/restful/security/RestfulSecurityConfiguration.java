package com.github.yingzhuo.springboot.side.restful.security;

import com.github.yingzhuo.springboot.side.web.config.AbstractFilterProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;

import java.io.Serializable;

@ConditionalOnWebApplication
@ConditionalOnProperty(prefix = "springboot.side.restful-security", name = "enabled", havingValue = "true", matchIfMissing = true)
@EnableConfigurationProperties(RestfulSecurityConfiguration.RestfulSecurityConfigurationProperties.class)
public class RestfulSecurityConfiguration {

    @Bean(name = "basicAuthenticationAccessTokenParser")
    @ConditionalOnMissingBean(AccessTokenParser.class)
    public AccessTokenParser accessTokenParser() {
        return new BasicAuthenticationAccessTokenParser();
    }

    @Bean
    @ConditionalOnMissingBean(UserDetailsLoader.class)
    public UserDetailsLoader userDetailsLoader() {
        return accessToken -> null;
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean(
            RestfulSecurityConfigurationProperties properties,
            AccessTokenParser accessTokenParser,
            UserDetailsLoader userDetailsLoader
    ) {
        RestfulSecurityFilter filter = new RestfulSecurityFilter();
        filter.setAccessTokenParser(accessTokenParser);
        filter.setUserDetailsLoader(userDetailsLoader);

        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setEnabled(properties.isEnabled());
        bean.setFilter(filter);
        bean.addUrlPatterns(properties.getUrlPatterns());
        bean.setName(properties.getFilterName());
        bean.setOrder(properties.getFilterOrder());
        return bean;
    }

    @ConfigurationProperties(prefix = "springboot.side.restful-security")
    public static class RestfulSecurityConfigurationProperties extends AbstractFilterProperties implements Serializable {
        private boolean enabled = false;

        public RestfulSecurityConfigurationProperties() {
            super.setEnabled(false);
            super.setFilterName(RestfulSecurityFilter.class.getSimpleName());
            super.setFilterOrder(Ordered.HIGHEST_PRECEDENCE);
            super.setUrlPatterns(new String[] {"/*"});
        }

        public boolean isEnabled() { return enabled; }
        public void setEnabled(boolean enabled) { this.enabled = enabled; }
    }

}
