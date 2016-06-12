package com.github.yingzhuo.springboot.side.patchca.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.Serializable;

@ConfigurationProperties(prefix = "springboot.side.patchca.background")
public class BackgroundProperties implements Serializable {

    private int r = 255;
    private int g = 255;
    private int b = 255;

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
}
