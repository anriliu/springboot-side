package com.github.yingzhuo.springboot.side.profile;

import org.springframework.beans.factory.InitializingBean;

import java.util.HashSet;
import java.util.Set;

public class ProfileValidator implements InitializingBean {

    private Set<String> currentProfiles;
    private final Set<Set<String>> mutualExclusionSet = new HashSet<>();

    @Override
    public void afterPropertiesSet() throws Exception {
        if (mutualExclusionSet.isEmpty()) {
            return;
        }

        if (currentProfiles == null || currentProfiles.isEmpty()) {
            return;
        }

        for (Set<String> group : mutualExclusionSet) {
            int matched = 0;
            for (String profile : currentProfiles) {
               if (group.contains(profile)) {
                   matched++;
               }
            }
            if (matched >= 2) {
                final String message = String.format("Profile config error! There are MUTUAL EXCLUSION: %s", group.toString());
                throw new ProfileConfigException(message);
            }
        }
    }

    public ProfileValidator addMutualExclusion(Set<String> profiles) {
        mutualExclusionSet.add(profiles);
        return this;
    }

    public Set<String> getCurrentProfiles() {
        return currentProfiles;
    }

    public void setCurrentProfiles(Set<String> currentProfiles) {
        this.currentProfiles = currentProfiles;
    }

    public Set<Set<String>> getMutualExclusionSet() {
        return mutualExclusionSet;
    }

    public static final class ProfileConfigException extends IllegalStateException {
        public ProfileConfigException(String message) {
            super(message);
        }
    }
}
