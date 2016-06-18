package com.github.yingzhuo.springboot.side.qiniuyun;

import com.qiniu.util.Auth;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.util.Assert;

import java.io.Serializable;

@ConditionalOnClass(Auth.class)
@ConditionalOnProperty(prefix = "springboot.side.qiniuyun", name = "enabled", havingValue = "true", matchIfMissing = true)
@EnableConfigurationProperties(QiniuyunConfiguration.QiniuyunProperties.class)
public class QiniuyunConfiguration {

    @Bean
    public QiniuyunManager qiniuyunManager(QiniuyunProperties properties) {
        if (properties.getMode() == Mode.MOCK) {
            return new MockQiniuyunManagerImpl(properties.getUrlPrefix());
        } else {
            SimpleQiniuyunManagerImpl bean = new SimpleQiniuyunManagerImpl();
            bean.setAk(properties.getAccessKey());
            bean.setSk(properties.getSecretKey());
            bean.setBucket(properties.getBucket());
            bean.setUrlPrefix(properties.getUrlPrefix());
            return bean;
        }
    }

    public enum Mode {
        GENERAL, MOCK
    }

    @ConfigurationProperties(prefix = "springboot.side.qiniuyun")
    public static class QiniuyunProperties implements Serializable, InitializingBean {
        private boolean enabled = true;
        private String bucket;
        private String accessKey;
        private String secretKey;
        private String urlPrefix;
        private Mode mode = Mode.GENERAL;

        @Override
        public void afterPropertiesSet() throws Exception {
            Assert.notNull(mode, "you should config 'springboot.side.qiniuyun.mode'.");

            if (mode == Mode.GENERAL) {
                Assert.hasText(urlPrefix, "you should config 'springboot.side.qiniuyun.url-prefix'.");
                Assert.hasText(bucket, "you should config 'springboot.side.qiniuyun.bucket'.");
                Assert.hasText(accessKey, "you should config 'springboot.side.qiniuyun.access-key'.");
                Assert.hasText(secretKey, "you should config 'springboot.side.qiniuyun.secret-key'.");
            }

            if (mode == Mode.MOCK) {
                urlPrefix = "http://mock.qiniuyun.com/";
            }

            if (urlPrefix != null && !urlPrefix.endsWith("/")) {
                urlPrefix += "/";
            }
        }

        public boolean isEnabled() {
            return enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }

        public String getBucket() {
            return bucket;
        }

        public void setBucket(String bucket) {
            this.bucket = bucket;
        }

        public String getAccessKey() {
            return accessKey;
        }

        public void setAccessKey(String accessKey) {
            this.accessKey = accessKey;
        }

        public String getSecretKey() {
            return secretKey;
        }

        public void setSecretKey(String secretKey) {
            this.secretKey = secretKey;
        }

        public String getUrlPrefix() {
            return urlPrefix;
        }

        public void setUrlPrefix(String urlPrefix) {
            this.urlPrefix = urlPrefix;
        }

        public Mode getMode() {
            return mode;
        }

        public void setMode(Mode mode) {
            this.mode = mode;
        }
    }
}
