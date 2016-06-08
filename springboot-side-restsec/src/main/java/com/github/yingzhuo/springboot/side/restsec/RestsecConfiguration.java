package com.github.yingzhuo.springboot.side.restsec;

import com.github.yingzhuo.springboot.side.restsec.core.AccessTokenParser;
import com.github.yingzhuo.springboot.side.restsec.core.RestsecFilter;
import com.github.yingzhuo.springboot.side.restsec.core.UserLikeLoader;
import com.github.yingzhuo.springboot.side.restsec.impl.BasicAuthenticationTokenParser;
import com.github.yingzhuo.springboot.side.restsec.impl.BearerAuthorizationTokenParser;
import com.github.yingzhuo.springboot.side.restsec.impl.BrokenUserLikeLoader;
import com.github.yingzhuo.springboot.side.restsec.impl.CompositeAccessTokenParser;
import com.github.yingzhuo.springboot.side.restsec.web.RestsecHandlerMethodArgumentResolver;
import com.github.yingzhuo.springboot.side.web.config.AbstractSkippableFilterProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.Arrays;
import java.util.List;

@ConditionalOnWebApplication
@ConditionalOnProperty(prefix = "springboot.side.restsec", name = "enabled", havingValue = "true", matchIfMissing = true)
@EnableConfigurationProperties(RestsecConfiguration.RestsecProperties.class)
public class RestsecConfiguration extends WebMvcConfigurerAdapter {

    @Bean
    @ConditionalOnMissingBean(AccessTokenParser.class)
    public AccessTokenParser accessTokenParser() {
        return new CompositeAccessTokenParser(Arrays.asList(
                new BasicAuthenticationTokenParser(),
                new BearerAuthorizationTokenParser()
        ));
    }

    @Bean
    public UserLikeLoader userLikeLoader() {
        return new BrokenUserLikeLoader();
    }

    @Bean
    public FilterRegistrationBean restsecFilter(RestsecProperties properties) {
        RestsecFilter filter = new RestsecFilter();


        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(filter);
        bean.setOrder(properties.getFilterOrder());
        bean.setUrlPatterns(Arrays.asList(properties.getUrlPatterns()));
        bean.setEnabled(properties.isEnabled());

        return bean;
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new RestsecHandlerMethodArgumentResolver());
    }

    @ConfigurationProperties(prefix = "springboot.side.restsec")
    public static class RestsecProperties extends AbstractSkippableFilterProperties {

        private boolean enabled = true;

        public RestsecProperties() {
            super.setSkipPatterns(new String[] {
                    "/**/*.js",
                    "/**/*.css",
                    "/**/*.ico",
                    "/**/*.jpg",
                    "/**/*.jpeg",
                    "/**/*.png",
                    "/**/*.gif",
                    "/**/*.swf"
            });
        }

        @Override
        public boolean isEnabled() {
            return enabled;
        }

        @Override
        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }
    }

}
