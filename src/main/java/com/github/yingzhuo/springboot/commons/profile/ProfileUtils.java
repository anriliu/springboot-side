package com.github.yingzhuo.springboot.commons.profile;

import com.github.yingzhuo.springboot.commons.func.CodeBlock;
import com.github.yingzhuo.springboot.commons.util.SpringUtils;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProfileUtils {

    public static List<String> getActiveProfiles() {
        return Collections.unmodifiableList(SpringUtils.getActiveProfiles());
    }

    public static Set<String> getActiveProfilesAsSet() {
        return Collections.unmodifiableSet(new HashSet<>(getActiveProfiles()));
    }

    public static void runIfPresent(String profile, CodeBlock codeBlock) {
        if (getActiveProfilesAsSet().contains(profile)) {
            codeBlock.run();
        }
    }

    public static void runIfAbsent(String profile, CodeBlock codeBlock) {
        if (!getActiveProfilesAsSet().contains(profile)) {
            codeBlock.run();
        }
    }
}
