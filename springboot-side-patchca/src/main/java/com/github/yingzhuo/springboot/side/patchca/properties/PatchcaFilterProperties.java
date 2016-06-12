package com.github.yingzhuo.springboot.side.patchca.properties;

import com.github.yingzhuo.springboot.side.patchca.PatchcaFilter;
import com.github.yingzhuo.springboot.side.web.config.AbstractFilterProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.Ordered;

import java.io.Serializable;

@ConfigurationProperties(prefix = "springboot.side.patchca")
public class PatchcaFilterProperties extends AbstractFilterProperties implements Serializable {

    private boolean enabled = true;
    private int width = 160;
    private int height = 70;
    private FilterType filterType = FilterType.CURVES;
    private String sessionAttributeName = "PATCHCA_SESSION_ATTRIBUTE_NAME";

    public PatchcaFilterProperties() {
        super.setUrlPatterns(new String[] {"/patchca.png", "/patchca"});
        super.setFilterName(PatchcaFilter.class.getName());
        super.setFilterOrder(Ordered.LOWEST_PRECEDENCE);
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public FilterType getFilterType() {
        return filterType;
    }

    public void setFilterType(FilterType filterType) {
        this.filterType = filterType;
    }

    public String getSessionAttributeName() {
        return sessionAttributeName;
    }

    public void setSessionAttributeName(String sessionAttributeName) {
        this.sessionAttributeName = sessionAttributeName;
    }
}
