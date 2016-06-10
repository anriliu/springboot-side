package com.github.yingzhuo.springboot.side.endpoint;

import org.springframework.boot.actuate.endpoint.Endpoint;
import org.springframework.boot.actuate.endpoint.EndpointProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import java.io.Serializable;
import java.util.List;

@ConditionalOnClass(EndpointProperties.class)
@EnableConfigurationProperties({
        EndpointConfiguration.EndpointsProperties.class,
        EndpointConfiguration.StartupDateProperties.class
})
public class EndpointConfiguration {

    @Bean
    @ConditionalOnProperty(prefix = "endpoints.endpoints", name = "enabled", havingValue = "true", matchIfMissing = true)
    public EndpointsEndpoint endpointsEndpoint(List<Endpoint> endpoints, EndpointsProperties properties) {
        EndpointsEndpoint bean = new EndpointsEndpoint(endpoints);
        bean.setEnabled(properties.isEnabled());
        bean.setId(properties.getId());
        bean.setSensitive(properties.isSensitive());
        bean.setSort(properties.isSort());
        return bean;
    }

    @ConfigurationProperties(prefix = "endpoints.endpoints")
    public static class EndpointsProperties implements Serializable {

        private String id = "endpoints";
        private boolean enabled = true;
        private boolean sensitive = false;
        private boolean sort = true;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public boolean isSensitive() {
            return sensitive;
        }

        public void setSensitive(boolean sensitive) {
            this.sensitive = sensitive;
        }

        public boolean isEnabled() {
            return enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }

        public boolean isSort() {
            return sort;
        }

        public void setSort(boolean sort) {
            this.sort = sort;
        }
    }

    // ------------------------------------------------------------------------------------------------

    @Bean
    @ConditionalOnProperty(prefix = "endpoints.startup", name = "enabled", havingValue = "true", matchIfMissing = true)
    public StartupDateEndpoint startupDateEndpoint(StartupDateProperties properties) {
        System.out.println(1);
        System.out.println(1);
        System.out.println(1);
        StartupDateEndpoint endpoint = new StartupDateEndpoint(properties.getId(), properties.isSensitive(), properties.isEnabled());
        endpoint.setPattern(properties.getPattern());
        return endpoint;
    }

    @ConfigurationProperties(prefix = "endpoints.startup")
    public static class StartupDateProperties implements Serializable {
        private String id = "startup";
        private boolean enabled = true;
        private boolean sensitive = false;
        private String pattern = "yyyy-MM-dd HH:mm:ss";

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public boolean isEnabled() {
            return enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }

        public boolean isSensitive() {
            return sensitive;
        }

        public void setSensitive(boolean sensitive) {
            this.sensitive = sensitive;
        }

        public String getPattern() {
            return pattern;
        }

        public void setPattern(String pattern) {
            this.pattern = pattern;
        }
    }
}
