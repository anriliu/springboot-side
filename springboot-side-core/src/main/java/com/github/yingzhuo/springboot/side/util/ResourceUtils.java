package com.github.yingzhuo.springboot.side.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ResourceUtils {

    private ResourceUtils() {
    }

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
