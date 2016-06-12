package com.github.yingzhuo.springboot.side.patchca.properties;

import com.github.yingzhuo.springboot.side.patchca.PatchcaFilter;
import com.github.yingzhuo.springboot.side.web.config.AbstractFilterProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.Ordered;

@ConfigurationProperties(prefix = "springboot.side.patchca")
public class PatchcaFilterProperties extends AbstractFilterProperties {

    private boolean enabled = true;
    private int r = 25;
    private int g = 60;
    private int b = 170;
    private int width = 150;
    private int height = 50;
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

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }

    public int getG() {
        return g;
    }

    public void setG(int g) {
        this.g = g;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
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
