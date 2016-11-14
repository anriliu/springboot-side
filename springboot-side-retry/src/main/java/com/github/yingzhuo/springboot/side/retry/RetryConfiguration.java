package com.github.yingzhuo.springboot.side.retry;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

import java.io.Serializable;

@EnableRetry
@ConditionalOnProperty(prefix = "springboot.side.retry-template", name = "retry", havingValue = "true", matchIfMissing = true)
@EnableConfigurationProperties(RetryConfiguration.RetryConfigurationProperties.class)
public class RetryConfiguration {

    @Bean
    @ConditionalOnMissingBean(RetryTemplate.class)
    public RetryTemplate retryTemplate(RetryConfigurationProperties properties) {
        FixedBackOffPolicy fixedBackOffPolicy = new FixedBackOffPolicy();
        fixedBackOffPolicy.setBackOffPeriod(properties.getBackOffPeriod());

        SimpleRetryPolicy retryPolicy = new SimpleRetryPolicy();
        retryPolicy.setMaxAttempts(properties.getMaxAttempts());

        RetryTemplate retryTemplate = new RetryTemplate();
        retryTemplate.setBackOffPolicy(fixedBackOffPolicy);
        retryTemplate.setRetryPolicy(retryPolicy);
        return retryTemplate;
    }

    @ConfigurationProperties(prefix = "springboot.side.retry-template")
    public static class RetryConfigurationProperties implements Serializable {
        private boolean enabled = true;
        private long backOffPeriod = 2000L;
        private int maxAttempts = 3;
        public boolean isEnabled() { return enabled; }
        public void setEnabled(boolean enabled) { this.enabled = enabled; }
        public long getBackOffPeriod() { return backOffPeriod; }
        public void setBackOffPeriod(long backOffPeriod) { this.backOffPeriod = backOffPeriod; }
        public int getMaxAttempts() { return maxAttempts; }
        public void setMaxAttempts(int maxAttempts) { this.maxAttempts = maxAttempts; }
    }

}
