package com.github.yingzhuo.springboot.side.chanzor;

import org.springframework.boot.actuate.endpoint.EndpointProperties;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;

@ConditionalOnClass(EndpointProperties.class)
public class ChanzorHealthEndpointConfiguration {

    @Bean(name = "chanzor")
    public HealthIndicator chanzorHealthIndicator(ChanzorManager chanzorService) {
        return () -> {
            Health.Builder builder = new Health.Builder();
            try {
                long overage = chanzorService.overage();
                builder.up();
                builder.withDetail("overage", overage);
            } catch (Exception e) {
                builder.down();
                builder.withDetail("overage", -1L);
            }
            return builder.build();
        };
    }

}
