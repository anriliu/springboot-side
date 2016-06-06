package com.github.yingzhuo.springboot.side.qiniuyun;

import com.github.yingzhuo.springboot.side.qiniuyun.exception.QiniuyunIOException;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.FileInfo;
import com.qiniu.util.Auth;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

import java.io.IOException;
import java.io.InputStream;

public class SimpleQiniuyunManagerImpl implements QiniuyunManager, InitializingBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(QiniuyunManager.class);

    private Auth auth;
    private String urlPrefix;
    private String bucket;
    private String ak;
    private String sk;
    private BucketManager bucketManager;
    private UploadManager uploadManager;

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.hasText(urlPrefix);
        Assert.hasText(bucket);
        Assert.hasText(ak);
        Assert.hasText(sk);
        auth = Auth.create(ak, sk);
        bucketManager = new BucketManager(auth);
        uploadManager = new UploadManager();
    }

    @Override
    public String upload(byte[] data, String key, boolean overwrite) {
        try {
            Response response = uploadManager.put(data, key, createToken(bucket, key, overwrite));
            UploadReturn result = response.jsonToObject(UploadReturn.class);
            LOGGER.debug("upload result: {}", result);
            return urlPrefix + key;
        } catch (QiniuException e) {
            throw new QiniuyunIOException(e.getMessage(), e);
        }
    }

    @Override
    public String upload(InputStream data, String key, boolean overwrite) {
        try {
            byte[] d = IOUtils.toByteArray(data);
            return upload(d, key, overwrite);
        } catch (IOException e) {
            throw new QiniuyunIOException(e.getMessage(), e);
        }
    }

    @Override
    public String rename(String fromKey, String toKey) {
        try {
            bucketManager.rename(bucket, fromKey, toKey);
            return urlPrefix + toKey;
        } catch (QiniuException e) {
            throw new QiniuyunIOException(e.getMessage(), e);
        }
    }

    @Override
    public boolean exists(String key) {
        try {
            FileInfo fileInfo = bucketManager.stat(bucket, key);
            return fileInfo != null;
        } catch (QiniuException e) {
            return false;
        }
    }

    @Override
    public void delete(String key) {
        try {
            bucketManager.delete(bucket, key);
        } catch (QiniuException e) {
            throw new QiniuyunIOException(e.getMessage(), e);
        }
    }

    @Override
    public void deleteByUrl(String url) {
        if (url.startsWith(urlPrefix)) {
            String key = url.substring(urlPrefix.length());
            delete(key);
        }
    }

    private String createToken(String bucket, String key, boolean overwrite) {
        if (overwrite) {
            return auth.uploadToken(bucket, key);
        } else {
            return auth.uploadToken(bucket);
        }
    }

    /* -------------------------------------------------------------------------------- */

    public Auth getAuth() {
        return auth;
    }

    public void setAuth(Auth auth) {
        this.auth = auth;
    }

    public String getUrlPrefix() {
        return urlPrefix;
    }

    public void setUrlPrefix(String urlPrefix) {
        this.urlPrefix = urlPrefix;
    }

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    public String getAk() {
        return ak;
    }

    public void setAk(String ak) {
        this.ak = ak;
    }

    public String getSk() {
        return sk;
    }

    public void setSk(String sk) {
        this.sk = sk;
    }

    public BucketManager getBucketManager() {
        return bucketManager;
    }

    public void setBucketManager(BucketManager bucketManager) {
        this.bucketManager = bucketManager;
    }

    public UploadManager getUploadManager() {
        return uploadManager;
    }

    public void setUploadManager(UploadManager uploadManager) {
        this.uploadManager = uploadManager;
    }
}
