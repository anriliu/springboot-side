package com.github.yingzhuo.springboot.side.patchca.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.Serializable;

@ConfigurationProperties(prefix = "springboot.side.patchca.text-renderer")
public class TextRendererProperties implements Serializable {

    private int leftMargin = 10;
    private int rightMargin = 10;
    private int topMargin = 5;
    private int bottomMargin = 5;

    public int getLeftMargin() {
        return leftMargin;
    }

    public void setLeftMargin(int leftMargin) {
        this.leftMargin = leftMargin;
    }

    public int getRightMargin() {
        return rightMargin;
    }

    public void setRightMargin(int rightMargin) {
        this.rightMargin = rightMargin;
    }

    public int getTopMargin() {
        return topMargin;
    }

    public void setTopMargin(int topMargin) {
        this.topMargin = topMargin;
    }

    public int getBottomMargin() {
        return bottomMargin;
    }

    public void setBottomMargin(int bottomMargin) {
        this.bottomMargin = bottomMargin;
    }
}
