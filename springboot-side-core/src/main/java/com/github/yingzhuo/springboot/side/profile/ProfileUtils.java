package com.github.yingzhuo.springboot.side.profile;

import com.github.yingzhuo.springboot.side.func.CodeBlock;
import com.github.yingzhuo.springboot.side.util.SpringUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProfileUtils {

    private ProfileUtils() {
    }

    public static List<String> getActiveProfiles() {
        return Collections.unmodifiableList(SpringUtils.getActiveProfiles());
    }

    public static Set<String> getActiveProfilesAsSet() {
        return Collections.unmodifiableSet(new HashSet<>(getActiveProfiles()));
    }

    public static boolean isProfileActived(String profile) {
        return !StringUtils.isBlank(profile) && getActiveProfilesAsSet().contains(profile);
    }

    public static boolean isProfileNotActived(String profile) {
        return !isProfileActived(profile);
    }

    public static void runIfPresent(String profile, CodeBlock codeBlock) {
        if (isProfileActived(profile)) {
            codeBlock.run();
        }
    }

    public static void runIfAbsent(String profile, CodeBlock codeBlock) {
        if (isProfileNotActived(profile)) {
            codeBlock.run();
        }
    }
}
