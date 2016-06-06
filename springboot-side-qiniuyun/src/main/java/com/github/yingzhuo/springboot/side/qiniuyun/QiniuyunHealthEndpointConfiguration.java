package com.github.yingzhuo.springboot.side.qiniuyun;

import com.qiniu.util.Auth;
import org.springframework.boot.actuate.endpoint.EndpointProperties;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;

import java.util.UUID;

@ConditionalOnClass({EndpointProperties.class, Auth.class})
@ConditionalOnProperty(prefix = "springboot.side.qiniuyun", name = "enabled", havingValue = "true", matchIfMissing = true)
public class QiniuyunHealthEndpointConfiguration {

    @Bean(name = "qiniuyun")
    public HealthIndicator qiniuyunHealthIndicator(QiniuyunManager qiniuyunManager, QiniuyunConfiguration.QiniuyunProperties qiniuyunProperties) {
        return () -> {
            Health.Builder builder = new Health.Builder();

            if (isReachable(qiniuyunManager)) {
                builder.up();
            } else {
                builder.down();
            }

            builder.withDetail("bucket", qiniuyunProperties.getBucket());
            builder.withDetail("access-key", qiniuyunProperties.getAccessKey());
            builder.withDetail("secret-key", "[projected]");
            builder.withDetail("url-prefix", qiniuyunProperties.getUrlPrefix());

            return builder.build();
        };
    }

    private boolean isReachable(QiniuyunManager qiniuyunManager) {
        try {
            qiniuyunManager.exists(UUID.randomUUID().toString());
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
