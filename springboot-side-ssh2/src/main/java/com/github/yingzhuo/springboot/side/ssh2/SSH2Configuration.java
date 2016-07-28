package com.github.yingzhuo.springboot.side.ssh2;

import ch.ethz.ssh2.Connection;
import com.github.yingzhuo.springboot.side.ssh2.core.DefaultShellExecutor;
import com.github.yingzhuo.springboot.side.ssh2.core.MockShellExecutor;
import com.github.yingzhuo.springboot.side.ssh2.core.ShellExecutor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import java.io.Serializable;

@ConditionalOnClass(Connection.class)
@EnableConfigurationProperties(SSH2Configuration.SSH2Properties.class)
@ConditionalOnProperty(prefix = "springboot.side.ssh2", name = "enabled", havingValue = "true", matchIfMissing = true)
public class SSH2Configuration {

    @Bean
    @ConditionalOnMissingBean(ShellExecutor.class)
    public ShellExecutor shellExecutor(SSH2Properties properties) {
        if (properties.getMode() == Mode.MOCK) {
            return new MockShellExecutor();
        }
        else {
            DefaultShellExecutor executor = new DefaultShellExecutor();
            executor.setHostname(properties.getHostname());
            executor.setPort(properties.getPort());
            executor.setUsername(properties.getUsername());
            executor.setPassword(properties.getPassword());
            return executor;
        }
    }

    public enum Mode {
        GENERAL, MOCK
    }

    @ConfigurationProperties(prefix = "springboot.side.ssh2")
    public static class SSH2Properties implements Serializable {

        private boolean enabled = true;
        private Mode mode = Mode.GENERAL;
        private String hostname = "127.0.0.1";
        private Integer port = 22;
        private String username = "root";
        private String password = "";

        public String getHostname() {
            return hostname;
        }

        public void setHostname(String hostname) {
            this.hostname = hostname;
        }

        public Integer getPort() {
            return port;
        }

        public void setPort(Integer port) {
            this.port = port;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public Mode getMode() {
            return mode;
        }

        public void setMode(Mode mode) {
            this.mode = mode;
        }

        public boolean isEnabled() {
            return enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }
    }
}
