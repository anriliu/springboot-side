package com.github.yingzhuo.springboot.side.util;

import com.github.yingzhuo.springboot.side.func.Runnable;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
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
import java.util.Set;

public class SpringUtils implements ApplicationContextAware, ApplicationRunner, Ordered {

    private static ApplicationContext APPCTX = null;
    private static ApplicationArguments ARGS = null;
    private static final PathMatcher DEFAULT_PATH_MATCHER = new AntPathMatcher();

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        APPCTX = applicationContext;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        ARGS = args;
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }

    /* ----------------------------------------------------------------------------------------- */

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

    public static <T> T getBean(Class<T> beanType) {
        return APPCTX.getBean(beanType);
    }

    public static <T> T getBean(Class<T> beanType, String beanName) {
        return APPCTX.getBean(beanName, beanType);
    }

    /* ----------------------------------------------------------------------------------------- */

    public static List<String> getActivedProfiles() {
        return Collections.unmodifiableList(Arrays.asList(getEnvironment().getActiveProfiles()));
    }

    public static Set<String> getActivedProfilesAsSet() {
        return Collections.unmodifiableSet(new HashSet<>(getActivedProfiles()));
    }

    public static boolean isProfileActived(String profile) {
        return !StringUtils.isBlank(profile) && getActivedProfilesAsSet().contains(profile);
    }

    public static boolean isProfileNotActived(String profile) {
        return !isProfileActived(profile);
    }

    public static void runIfProfileActived(String profile, Runnable codeBlock) {
        if (isProfileActived(profile)) {
            codeBlock.run();
        }
    }

    public static void runIfProfileNotActived(String profile, Runnable codeBlock) {
        if (isProfileNotActived(profile)) {
            codeBlock.run();
        }
    }

    /* ----------------------------------------------------------------------------------------- */

    public static String[] getRawSourceArgs() {
        return ARGS.getSourceArgs();
    }

    public static Set<String> getOptionNames() {
        return ARGS.getOptionNames();
    }

    public boolean containsOption(String name) {
        return ARGS.containsOption(name);
    }

    public static List<String> getOptionValues(String name) {
        return ARGS.getOptionValues(name);
    }

    public List<String> getNonOptionArgs() {
        return ARGS.getNonOptionArgs();
    }

    /* ----------------------------------------------------------------------------------------- */

    public PathMatcher getDefaultPathMatcher() {
        return DEFAULT_PATH_MATCHER;
    }

    /* ----------------------------------------------------------------------------------------- */

    public static Resource loadOneByLocation(String location) {
        if (StringUtils.isBlank(location)) {
            return null;
        }
        return SpringUtils.getResourcePatternResolver().getResource(location);
    }

    public static List<Resource> loadAllByPattern(String locationPattern) {
        if (StringUtils.isBlank(locationPattern)) {
            return Collections.emptyList();
        }

        try {
            return Collections.unmodifiableList(Arrays.asList(SpringUtils.getResourcePatternResolver().getResources(locationPattern)));
        } catch (IOException ioe) {
            throw new IllegalArgumentException(ioe.getCause());
        }
    }
}
