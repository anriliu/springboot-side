package com.github.yingzhuo.springboot.side.patchca.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.Serializable;

@ConfigurationProperties(prefix = "springboot.side.patchca.font")
public class FontProperties implements Serializable {

    protected String[] families = new String[] {"Verdana", "Tahoma"};
    protected int minSize = 45;
    protected int maxSize = 45;

    public String[] getFamilies() {
        return families;
    }

    public void setFamilies(String[] families) {
        this.families = families;
    }

    public int getMinSize() {
        return minSize;
    }

    public void setMinSize(int minSize) {
        this.minSize = minSize;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }
}
