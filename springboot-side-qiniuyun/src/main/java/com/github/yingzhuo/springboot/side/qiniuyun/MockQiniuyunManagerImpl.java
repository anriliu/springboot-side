package com.github.yingzhuo.springboot.side.qiniuyun;

import java.io.InputStream;

public class MockQiniuyunManagerImpl implements QiniuyunManager {

    private final String urlPrefix;

    public MockQiniuyunManagerImpl(String urlPrefix) {
        this.urlPrefix = urlPrefix;
    }

    @Override
    public String upload(byte[] data, String key, boolean overwrite) {
        return urlPrefix + key;
    }

    @Override
    public String upload(InputStream data, String key, boolean overwrite) {
        return urlPrefix + key;
    }

    @Override
    public String rename(String fromKey, String toKey) {
        return urlPrefix + toKey;
    }

    @Override
    public boolean exists(String key) {
        return true;
    }

    @Override
    public void delete(String key) {
        // 无代码
    }

    @Override
    public void deleteByUrl(String url) {
        // 无代码
    }
}
