package com.github.yingzhuo.springboot.side.web.servlet;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@ConditionalOnWebApplication
@ConditionalOnProperty(prefix = "springboot.side.captcha-servlet", name = "enabled", havingValue = "true", matchIfMissing = false)
@EnableConfigurationProperties(CaptchaServletProperties.class)
public class CaptchaServletConfiguration {

    @Bean
    public ServletRegistrationBean captchaServletFilter(CaptchaServletProperties properties) {
        CaptchaServlet servlet = new CaptchaServlet();
        servlet.setWidth(properties.getWidth());
        servlet.setHeight(properties.getHeight());
        servlet.setSessionAttributeName(properties.getSessionAttributeName());

        ServletRegistrationBean bean = new ServletRegistrationBean();
        bean.setServlet(servlet);
        bean.addUrlMappings(properties.getUrlMappings());
        bean.setName(properties.getServletName());

        return bean;
    }

}
