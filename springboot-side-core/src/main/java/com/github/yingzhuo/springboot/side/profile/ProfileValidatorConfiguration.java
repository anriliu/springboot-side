package com.github.yingzhuo.springboot.side.profile;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@ConditionalOnMissingBean(ProfileValidator.class)
@ConditionalOnProperty(prefix = "springboot.side.profile-validator", name = "enabled", havingValue = "true", matchIfMissing = true)
@EnableConfigurationProperties(ProfileValidatorConfiguration.ProfileValidatorProperties.class)
public class ProfileValidatorConfiguration {

    @Bean
    public ProfileValidator profileValidator(ProfileValidatorProperties properties, Environment environment) {
        ProfileValidator bean = new ProfileValidator();
        bean.setCurrentProfiles(new HashSet<>(Arrays.asList(environment.getActiveProfiles())));
        for (Collection<String> group : properties.getMutualExclusionMap().values()) {
            bean.addMutualExclusion(new HashSet<>(group));
        }
        return bean;
    }

    @ConfigurationProperties(prefix = "springboot.side.profile-validator")
    public static class ProfileValidatorProperties {
        private boolean enabled = true;
        private Map<String, Set<String>> mutualExclusionMap = new HashMap<>();

        public boolean isEnabled() {
            return enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }

        public Map<String, Set<String>> getMutualExclusionMap() {
            return mutualExclusionMap;
        }

        public void setMutualExclusionMap(Map<String, Set<String>> mutualExclusionMap) {
            this.mutualExclusionMap = mutualExclusionMap;
        }

    }
}
