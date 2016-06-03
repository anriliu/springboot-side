package com.github.yingzhuo.springboot.commons.web.servlet;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@EnableConfigurationProperties(CaptchaServletProperties.class)
@ConditionalOnProperty(prefix = "springboot.commons.captcha-servlet", name = "enabled", havingValue = "true", matchIfMissing = false)
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
