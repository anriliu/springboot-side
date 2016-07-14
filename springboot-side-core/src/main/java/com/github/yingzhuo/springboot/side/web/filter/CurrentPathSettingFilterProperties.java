package com.github.yingzhuo.springboot.side.web.filter;

import com.github.yingzhuo.springboot.side.web.AttributeScope;
import com.github.yingzhuo.springboot.side.web.config.AbstractFilterProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.Ordered;

@ConfigurationProperties(prefix = "springboot.side.currentpath-setting-filter")
public class CurrentPathSettingFilterProperties extends AbstractFilterProperties {

    private AttributeScope scope = AttributeScope.REQUEST;
    private String currentPathAttributeName = "CURRENT_PATH";

    public CurrentPathSettingFilterProperties() {
        super.setEnabled(true);
        super.setFilterName(BasePathSettingFilter.class.getSimpleName());
        super.setFilterOrder(Ordered.LOWEST_PRECEDENCE - 1);
        super.setUrlPatterns(new String[] {"/*"});
    }

    public AttributeScope getScope() {
        return scope;
    }

    public void setScope(AttributeScope scope) {
        this.scope = scope;
    }

    public String getCurrentPathAttributeName() {
        return currentPathAttributeName;
    }

    public void setCurrentPathAttributeName(String currentPathAttributeName) {
        this.currentPathAttributeName = currentPathAttributeName;
    }

}
