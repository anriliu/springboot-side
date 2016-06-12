package com.github.yingzhuo.springboot.side.patchca.properties;

import org.patchca.color.ColorFactory;
import org.patchca.color.RandomColorFactory;
import org.patchca.color.SingleColorFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.awt.*;
import java.io.Serializable;

@ConfigurationProperties(prefix = "springboot.side.patchca.foreground")
public class ForegroundProperties implements Serializable {

    private int r = -1;
    private int g = -1;
    private int b = -1;

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

    public ColorFactory createColorFactory() {
        if (r < 0 || g < 0 || b < 0) {
            return new RandomColorFactory();
        } else {
            return new SingleColorFactory(new Color(r, g, b));
        }
    }
}
