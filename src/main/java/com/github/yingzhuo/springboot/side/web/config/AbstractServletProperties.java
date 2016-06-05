package com.github.yingzhuo.springboot.side.web.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

import java.io.Serializable;

public abstract class AbstractServletProperties implements Serializable, InitializingBean {

    private boolean enabled = true;
    private String[] urlMappings;
    private String servletName;

    public AbstractServletProperties() {
        super();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(servletName);
        Assert.notNull(urlMappings);
        Assert.noNullElements(urlMappings);
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String[] getUrlMappings() {
        return urlMappings;
    }

    public void setUrlMappings(String[] urlMappings) {
        this.urlMappings = urlMappings;
    }

    public String getServletName() {
        return servletName;
    }

    public void setServletName(String servletName) {
        this.servletName = servletName;
    }
}
