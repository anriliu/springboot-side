package com.github.yingzhuo.springboot.side;

import com.github.yingzhuo.springboot.side.util.IPHandlerMethodArgumentResolver;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.util.UrlPathHelper;

import java.io.Serializable;
import java.util.List;

@ConditionalOnWebApplication
@ConditionalOnProperty(prefix = "springboot.side.common.webmvc", name = "enabled", havingValue = "true", matchIfMissing = true)
@EnableConfigurationProperties(SpringBootSideWebMvcConfiguration.SpringBootSideWebMvcConfigurationProperties.class)
public class SpringBootSideWebMvcConfiguration extends WebMvcConfigurerAdapter {

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        UrlPathHelper helper = new UrlPathHelper();
        helper.setRemoveSemicolonContent(false);
        helper.setDefaultEncoding("UTF-8");
        configurer.setUrlPathHelper(helper);
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new IPHandlerMethodArgumentResolver());
    }

    @ConfigurationProperties(prefix = "springboot.side.common.webmvc")
    public static class SpringBootSideWebMvcConfigurationProperties implements Serializable {
        private boolean enabled = true;
        public boolean isEnabled() { return enabled; }
        public void setEnabled(boolean enabled) { this.enabled = enabled; }
    }

}