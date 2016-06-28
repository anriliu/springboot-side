package com.github.yingzhuo.springboot.side;

import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import java.io.Serializable;

@ConditionalOnProperty(prefix = "springboot.side.common", name = "enabled", havingValue = "true", matchIfMissing = true)
@EnableConfigurationProperties(SpringBootSideCommonConfiguration.SpringBootSideCommonConfigurationProperties.class)
public class SpringBootSideCommonConfiguration {

    @Bean(name = "commonDefaultAdvisorAutoProxyCreator")
    @ConditionalOnMissingBean(DefaultAdvisorAutoProxyCreator.class)
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        return new DefaultAdvisorAutoProxyCreator();
    }

    @ConfigurationProperties(prefix = "springboot.side.common")
    public static class SpringBootSideCommonConfigurationProperties implements Serializable {
        private boolean enabled = true;
        public boolean isEnabled() { return enabled; }
        public void setEnabled(boolean enabled) { this.enabled = enabled;}
    }

}
