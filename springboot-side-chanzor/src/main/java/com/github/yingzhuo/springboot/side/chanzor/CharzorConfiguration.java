package com.github.yingzhuo.springboot.side.chanzor;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.util.Assert;

import java.io.Serializable;

@EnableConfigurationProperties(CharzorConfiguration.ChanzorProperties.class)
@ConditionalOnProperty(prefix = "springboot.side.chanzor", name = "enabled", havingValue = "true", matchIfMissing = true)
public class CharzorConfiguration {

    @Bean
    public ChanzorManager chanzorManager(ChanzorProperties properties) {
        if (properties.getMode() == Mode.MOCK) {
            return new MockChanzorManager();
        } else {
            SimpleChanzorManager manager = new SimpleChanzorManager();
            manager.setAccount(properties.getAccount());
            manager.setPassword(properties.getPassword());
            manager.setUrl(properties.getUrl());
            return manager;
        }

    }

    public enum Mode {
        GENERAL, MOCK
    }

    @ConfigurationProperties(prefix = "springboot.side.chanzor")
    public static class ChanzorProperties implements Serializable, InitializingBean {
        private boolean enabled = true;
        private String url = "http://sms.chanzor.com:8001/sms.aspx";
        private Mode mode = Mode.GENERAL;
        private String account;
        private String password;

        @Override
        public void afterPropertiesSet() throws Exception {
            Assert.notNull(mode);
            if (mode == Mode.GENERAL) {
                Assert.hasText(url);
                Assert.hasText(account);
                Assert.hasText(password);
            }
        }

        public boolean isEnabled() {
            return enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public Mode getMode() {
            return mode;
        }

        public void setMode(Mode mode) {
            this.mode = mode;
        }

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}
