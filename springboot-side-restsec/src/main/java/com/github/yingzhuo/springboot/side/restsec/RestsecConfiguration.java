package com.github.yingzhuo.springboot.side.restsec;

import com.github.yingzhuo.springboot.side.restsec.advice.MethodInterceptor;
import com.github.yingzhuo.springboot.side.restsec.annotation.RequiresAuthentication;
import com.github.yingzhuo.springboot.side.restsec.annotation.RequiresGuest;
import com.github.yingzhuo.springboot.side.restsec.annotation.RequiresPermissions;
import com.github.yingzhuo.springboot.side.restsec.annotation.RequiresRoles;
import com.github.yingzhuo.springboot.side.restsec.core.AccessTokenParser;
import com.github.yingzhuo.springboot.side.restsec.core.RestsecFilter;
import com.github.yingzhuo.springboot.side.restsec.core.UserLikeLoader;
import com.github.yingzhuo.springboot.side.restsec.impl.BasicAuthenticationTokenParser;
import com.github.yingzhuo.springboot.side.restsec.impl.BearerAuthorizationTokenParser;
import com.github.yingzhuo.springboot.side.restsec.impl.CompositeAccessTokenParser;
import com.github.yingzhuo.springboot.side.restsec.impl.NullReturningUserLikeLoader;
import com.github.yingzhuo.springboot.side.restsec.web.RestsecHandlerMethodArgumentResolver;
import com.github.yingzhuo.springboot.side.web.config.AbstractSkippableFilterProperties;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.aop.support.StaticMethodMatcherPointcutAdvisor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

@ConditionalOnWebApplication
@ConditionalOnProperty(prefix = "springboot.side.restsec", name = "enabled", havingValue = "true", matchIfMissing = true)
@EnableConfigurationProperties(RestsecConfiguration.RestsecProperties.class)
public class RestsecConfiguration extends WebMvcConfigurerAdapter {

    @Bean
    @ConditionalOnMissingBean(DefaultAdvisorAutoProxyCreator.class)
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        return new DefaultAdvisorAutoProxyCreator();
    }

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
        return new NullReturningUserLikeLoader();
    }

    @Bean
    public FilterRegistrationBean restsecFilter(RestsecProperties properties, AccessTokenParser accessTokenParser, UserLikeLoader userLikeLoader) {
        RestsecFilter filter = new RestsecFilter();
        filter.setAccessTokenParser(accessTokenParser);
        filter.setUserLikeLoader(userLikeLoader);
        filter.setSkipPatterns(properties.getSkipPatterns());

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

    @Bean
    public static StaticMethodMatcherPointcutAdvisor restsecStaticMethodMatcherPointcutAdvisor() {
        return new StaticMethodMatcherPointcutAdvisor() {
            {   // init
                setOrder(Ordered.LOWEST_PRECEDENCE);
                setAdvice(new MethodInterceptor());
            }

            @Override
            public boolean matches(Method method, Class<?> targetClass) {
                boolean con1 = method.getAnnotation(RequiresAuthentication.class) != null;
                boolean con2 = method.getAnnotation(RequiresGuest.class) != null;
                boolean con3 = method.getAnnotation(RequiresPermissions.class) != null;
                boolean con4 = method.getAnnotation(RequiresRoles.class) != null;
                return (con1 || con2 || con3 || con4);
            }
        };
    }

    @ConfigurationProperties(prefix = "springboot.side.restsec")
    public static class RestsecProperties extends AbstractSkippableFilterProperties {
        private boolean enabled = true;

        public RestsecProperties() {
            super.setFilterName(RestsecFilter.class.getSimpleName());
            super.setFilterOrder(Ordered.HIGHEST_PRECEDENCE);
            super.setUrlPatterns(new String[]{"/*"});
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
