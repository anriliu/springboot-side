package com.github.yingzhuo.springboot.commons.web.filter;

import com.github.yingzhuo.springboot.commons.web.config.AbstractFilterProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.Ordered;

import java.util.Arrays;

@ConfigurationProperties(prefix = "springboot.commons.http-context-holding-filter")
public class HttpContextHoldingProperties extends AbstractFilterProperties {

    public HttpContextHoldingProperties() {
        super.setEnabled(true);
        super.setFilterName(HttpContextHoldingFilter.class.getSimpleName());
        super.setFilterOrder(Ordered.HIGHEST_PRECEDENCE);
        super.setUrlPatterns(Arrays.asList("/*"));
    }

}
