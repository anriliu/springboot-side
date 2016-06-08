package com.github.yingzhuo.springboot.side.web.config;

public abstract class AbstractSkippableFilterProperties extends AbstractFilterProperties {

    private String[] skipPatterns = new String[0];

    public String[] getSkipPatterns() {
        return skipPatterns;
    }

    public void setSkipPatterns(String[] skipPatterns) {
        this.skipPatterns = skipPatterns;
    }

}
