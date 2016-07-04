package com.github.yingzhuo.springboot.side.web.filter;

import com.github.yingzhuo.springboot.side.web.config.AbstractFilterProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.core.Ordered;

@ConditionalOnWebApplication
@ConditionalOnProperty(prefix = "springboot.side.basepath-setting-filter", name = "enabled", havingValue = "true", matchIfMissing = true)
public class BasePathSettingFilterProperties extends AbstractFilterProperties {

    private String[] basepathAttributeNames = new String[] { "webroot", "WEBROOT", "basePath", "BASEPATH" };

    public BasePathSettingFilterProperties() {
        super.setEnabled(true);
        super.setFilterName(BasePathSettingFilter.class.getSimpleName());
        super.setFilterOrder(Ordered.LOWEST_PRECEDENCE);
        super.setUrlPatterns(new String[] {"/*"});
    }

    public String[] getBasepathAttributeNames() {
        return basepathAttributeNames;
    }

    public void setBasepathAttributeNames(String[] basepathAttributeNames) {
        this.basepathAttributeNames = basepathAttributeNames;
    }

}
