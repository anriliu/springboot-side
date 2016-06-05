package com.github.yingzhuo.springboot.side.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

@ConditionalOnMissingBean(ProfileValidator.class)
@ConditionalOnProperty(prefix = "springboot.side.profile-validator", name = "enabled", havingValue = "true", matchIfMissing = true)
@EnableConfigurationProperties(ProfileValidatorProperties.class)
public class ProfileValidatorConfiguration {

    @Autowired
    private ProfileValidatorProperties properties;

    @Autowired
    private Environment environment;

    @Bean
    public ProfileValidator profileValidator() {

        ProfileValidator bean = new ProfileValidator();

        bean.setCurrentProfiles(new HashSet<>(Arrays.asList(environment.getActiveProfiles())));

        for (Collection<String> group : properties.getMutualExclusionMap().values()) {
            bean.addMutualExclusion(new HashSet<>(group));
        }

        return bean;
    }

}
