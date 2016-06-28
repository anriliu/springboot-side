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
    public HealthIndicator qiniuyunHealthIndicator(QiniuyunManager qiniuyunManager, QiniuyunConfiguration.QiniuyunProperties properties) {
        return () -> {
            Health.Builder builder = new Health.Builder();

            if (isReachable(qiniuyunManager)) {
                builder.up();
            } else {
                builder.down();
            }


            builder.withDetail("mode", properties.getMode());
            builder.withDetail("bucket", properties.getBucket() != null ? properties.getBucket() : "[unknown]");
            builder.withDetail("access-key", properties.getAccessKey() != null ? properties.getAccessKey() : "[unknown]");
            builder.withDetail("secret-key", "[projected]");
            builder.withDetail("url-prefix", properties.getUrlPrefix() != null ? properties.getUrlPrefix() : "[unknown]");

            return builder.build();
        };
    }

    private boolean isReachable(QiniuyunManager qiniuyunManager) {
        try {
            qiniuyunManager.exists(randomQiniuKey());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private String randomQiniuKey() {
        StringBuilder builder = new StringBuilder();
        builder.append(UUID.randomUUID().toString());
        builder.append(UUID.randomUUID().toString());
        builder.append(UUID.randomUUID().toString());
        return builder.toString();
    }
}
