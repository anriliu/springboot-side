package com.github.yingzhuo.springboot.commons.profile;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@ConfigurationProperties(prefix = "springboot.commons.profile-validator")
public class ProfileValidatorProperties {

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
