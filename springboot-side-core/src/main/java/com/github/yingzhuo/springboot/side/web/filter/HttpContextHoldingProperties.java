package com.github.yingzhuo.springboot.side.web.filter;

import com.github.yingzhuo.springboot.side.web.config.AbstractFilterProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.Ordered;

@ConfigurationProperties(prefix = "springboot.side.http-context-holding-filter")
public class HttpContextHoldingProperties extends AbstractFilterProperties {

    public HttpContextHoldingProperties() {
        super.setEnabled(true);
        super.setFilterName(HttpContextHoldingFilter.class.getSimpleName());
        super.setFilterOrder(Ordered.HIGHEST_PRECEDENCE);
        super.setUrlPatterns(new String[] {"/*"});
    }

}
