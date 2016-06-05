package com.github.yingzhuo.springboot.side.web.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

import java.io.Serializable;

public abstract class AbstractFilterProperties implements Serializable, InitializingBean {

    private boolean enabled = true;
    private String filterName;
    private int filterOrder;
    private String[] urlPatterns;

    public AbstractFilterProperties() {
        super();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(filterName);
        Assert.notNull(urlPatterns);
        Assert.noNullElements(urlPatterns);
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getFilterName() {
        return filterName;
    }

    public void setFilterName(String filterName) {
        this.filterName = filterName;
    }

    public int getFilterOrder() {
        return filterOrder;
    }

    public void setFilterOrder(int filterOrder) {
        this.filterOrder = filterOrder;
    }

    public String[] getUrlPatterns() {
        return urlPatterns;
    }

    public void setUrlPatterns(String[] urlPatterns) {
        this.urlPatterns = urlPatterns;
    }
}
