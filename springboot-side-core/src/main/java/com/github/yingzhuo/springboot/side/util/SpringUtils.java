package com.github.yingzhuo.springboot.side.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SpringUtils implements ApplicationContextAware {

    private static ApplicationContext APPCTX = null;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        APPCTX = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        return APPCTX;
    }

    public static ApplicationEventPublisher getApplicationEventPublisher() {
        return APPCTX;
    }

    public static ResourcePatternResolver getResourcePatternResolver() {
        return APPCTX;
    }

    public static ResourceLoader getResourceLoader() {
        return APPCTX;
    }

    public static MessageSource getMessageSource() {
        return APPCTX;
    }

    public static Environment getEnvironment() {
        return APPCTX.getEnvironment();
    }

    public static List<String> getActiveProfiles() {
        return Collections.unmodifiableList(Arrays.asList(getEnvironment().getActiveProfiles()));
    }

}
