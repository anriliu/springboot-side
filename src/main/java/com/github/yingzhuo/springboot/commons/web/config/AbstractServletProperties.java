package com.github.yingzhuo.springboot.commons.web.config;

import java.io.Serializable;

public abstract class AbstractServletProperties implements Serializable {

    private boolean enabled = true;
    private String[] UrlMappings;
    private String servletName;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String[] getUrlMappings() {
        return UrlMappings;
    }

    public void setUrlMappings(String[] urlMappings) {
        UrlMappings = urlMappings;
    }

    public String getServletName() {
        return servletName;
    }

    public void setServletName(String servletName) {
        this.servletName = servletName;
    }
}
