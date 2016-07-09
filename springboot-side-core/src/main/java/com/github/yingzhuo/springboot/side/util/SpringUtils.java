package com.github.yingzhuo.springboot.side.util;

import com.github.yingzhuo.springboot.side.func.Runnable;
import org.springframework.beans.factory.BeanDefinitionStoreException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.core.Ordered;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

public final class SpringUtils extends ApplicationObjectSupport
        implements EnvironmentAware, ApplicationRunner, Ordered {

    public static final SpringUtils INSTANCE = new SpringUtils();

    private static final PathMatcher DEFAULT_PATH_MATCHER = new AntPathMatcher();
    private static Environment ENVIRONMENT;
    private static SpringUtils THIS;
    private static ApplicationArguments APPLICATION_ARGUMENTS;

    private SpringUtils() {
        SpringUtils.THIS = this;
    }

    @Override
    public void setEnvironment(Environment environment) {
        SpringUtils.ENVIRONMENT = environment;
    }

    @Override
    protected Class<?> requiredContextClass() {
        return ApplicationContext.class;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        SpringUtils.APPLICATION_ARGUMENTS = args;
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }

    // ------------------------------------------------------------------------------------

    public static ApplicationContext getAppCtx() {
        return THIS.getApplicationContext();
    }

    private static MessageSourceAccessor getMessageSourceAccessor2() {
        return THIS.getMessageSourceAccessor();
    }

    public static ApplicationEventPublisher getApplicationEventPublisher() {
        return getAppCtx();
    }

    public static ResourcePatternResolver getResourcePatternResolver() {
        return getAppCtx();
    }

    public static ResourceLoader getResourceLoader() {
        return getAppCtx();
    }

    public static MessageSource getMessageSource() {
        return getAppCtx();
    }

    public static Environment getEnvironment() {
        return ENVIRONMENT;
    }

    public static <T> T getBean(Class<T> beanType) {
        return getAppCtx().getBean(beanType);
    }

    public static <T> T getBean(Class<T> beanType, String beanName) {
        return getAppCtx().getBean(beanName, beanType);
    }

    /* ----------------------------------------------------------------------------------------- */

    public static List<String> getActivedProfiles() {
        return Collections.unmodifiableList(Arrays.asList(getEnvironment().getActiveProfiles()));
    }

    public static Set<String> getActivedProfilesAsSet() {
        return Collections.unmodifiableSet(new HashSet<>(getActivedProfiles()));
    }

    public static boolean acceptsProfiles(String... profiles) {
        for (String profile : profiles) {
            if (!getEnvironment().acceptsProfiles(profile)) {
                return false;
            }
        }
        return true;
    }

    public static void runIfProfileActived(String profile, Runnable runnable) {
        if (acceptsProfiles(profile)) {
            runnable.run();
        }
    }

    public static void runIfProfileNotActived(String profile, Runnable runnable) {
        if (!acceptsProfiles(profile)) {
            runnable.run();
        }
    }

    /* ----------------------------------------------------------------------------------------- */

    public static String[] getRawSourceArgs() {
        return APPLICATION_ARGUMENTS.getSourceArgs();
    }

    public static Set<String> getOptionNames() {
        return APPLICATION_ARGUMENTS.getOptionNames();
    }

    public boolean containsOption(String name) {
        return APPLICATION_ARGUMENTS.containsOption(name);
    }

    public static List<String> getOptionValues(String name) {
        return APPLICATION_ARGUMENTS.getOptionValues(name);
    }

    public List<String> getNonOptionArgs() {
        return APPLICATION_ARGUMENTS.getNonOptionArgs();
    }

    /* ----------------------------------------------------------------------------------------- */

    public static PathMatcher getDefaultPathMatcher() {
        return DEFAULT_PATH_MATCHER;
    }

    /* ----------------------------------------------------------------------------------------- */

    public static String getMessage(String code, String defaultMessage) {
        return getMessageSourceAccessor2().getMessage(code, defaultMessage);
    }

    public static String getMessage(String code, String defaultMessage, Locale locale) {
        return  getMessageSourceAccessor2().getMessage(code, null, defaultMessage, locale);
    }

    public static String getMessage(String code, Object[] args, String defaultMessage) {
        return getMessageSourceAccessor2().getMessage(code, args, defaultMessage);
    }

    public static String getMessage(String code, Object[] args, String defaultMessage, Locale locale) {
        return getMessageSourceAccessor2().getMessage(code, args, defaultMessage, locale);
    }

    public static String getMessage(String code) throws NoSuchMessageException {
        return getMessageSourceAccessor2().getMessage(code);
    }

    public static String getMessage(String code, Locale locale) throws NoSuchMessageException {
        return getMessageSourceAccessor2().getMessage(code, locale);
    }

    public static String getMessage(String code, Object[] args) throws NoSuchMessageException {
        return getMessageSourceAccessor2().getMessage(code, args);
    }

    public static String getMessage(String code, Object[] args, Locale locale) throws NoSuchMessageException {
        return getMessageSourceAccessor2().getMessage(code, args, locale);
    }

    public static String getMessage(MessageSourceResolvable resolvable) throws NoSuchMessageException {
        return getMessageSourceAccessor2().getMessage(resolvable);
    }

    public static String getMessage(MessageSourceResolvable resolvable, Locale locale) throws NoSuchMessageException {
        return getMessageSourceAccessor2().getMessage(resolvable, locale);
    }

    /* ----------------------------------------------------------------------------------------- */

    public static Resource loadResourceByLocation(String location) {
        return getResourcePatternResolver().getResource(location);
    }

    public static List<Resource> loadResourcesByLocations(String locationPattern) {
        try {
            return Collections.unmodifiableList(Arrays.asList(getResourcePatternResolver().getResources(locationPattern)));
        } catch (IOException ioe) {
            throw new IllegalArgumentException(ioe.getCause());
        }
    }

    /* ----------------------------------------------------------------------------------------- */

    public static void registerBean(String beanName, BeanDefinition beanDefinition) {
        try {
            ConfigurableApplicationContext configurableApplicationContext = (ConfigurableApplicationContext) getAppCtx();
            DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) configurableApplicationContext.getBeanFactory();
            beanFactory.registerBeanDefinition(beanName, beanDefinition);
        } catch (IllegalStateException | BeanDefinitionStoreException e) {
            throw e;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }
}
