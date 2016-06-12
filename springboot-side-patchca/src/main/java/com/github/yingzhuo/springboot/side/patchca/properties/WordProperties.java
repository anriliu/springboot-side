package com.github.yingzhuo.springboot.side.patchca.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.Serializable;

@ConfigurationProperties(prefix = "springboot.side.patchca.word")
public class WordProperties implements Serializable {

    private String wideCharacters = "wm";
    private String characters = "abcdefghijklmnopqrstuvwxyz0123456789";
    private int minLength = 6;
    private int maxLength = 6;

    public String getWideCharacters() {
        return wideCharacters;
    }

    public void setWideCharacters(String wideCharacters) {
        this.wideCharacters = wideCharacters;
    }

    public String getCharacters() {
        return characters;
    }

    public void setCharacters(String characters) {
        this.characters = characters;
    }

    public int getMinLength() {
        return minLength;
    }

    public void setMinLength(int minLength) {
        this.minLength = minLength;
    }

    public int getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(int maxLength) {
        this.maxLength = maxLength;
    }
}
