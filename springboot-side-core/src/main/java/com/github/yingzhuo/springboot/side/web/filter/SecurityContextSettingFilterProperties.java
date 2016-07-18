package com.github.yingzhuo.springboot.side.web.filter;

import com.github.yingzhuo.springboot.side.web.AttributeScope;
import com.github.yingzhuo.springboot.side.web.config.AbstractFilterProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.Ordered;

@ConfigurationProperties(prefix = "springboot.side.security-context-setting-filter")
public class SecurityContextSettingFilterProperties extends AbstractFilterProperties {

    private AttributeScope scope = AttributeScope.REQUEST;
    private String userDetailsAttributeName = "USER_DETAILS";
    private String userDetailsUsernameAttributeName = "USER_DETAILS_USERNAME";
    private String userDetailsAuthoritiesAttributeName = "USER_DETAILS_AUTHORITIES";

    public SecurityContextSettingFilterProperties() {
        super.setEnabled(true);
        super.setFilterName(RequestLoggingFilter.class.getSimpleName());
        super.setFilterOrder(Ordered.LOWEST_PRECEDENCE);
        super.setUrlPatterns(new String[] {"/*"});
    }

    public AttributeScope getScope() {
        return scope;
    }

    public void setScope(AttributeScope scope) {
        this.scope = scope;
    }

    public String getUserDetailsAttributeName() {
        return userDetailsAttributeName;
    }

    public void setUserDetailsAttributeName(String userDetailsAttributeName) {
        this.userDetailsAttributeName = userDetailsAttributeName;
    }

    public String getUserDetailsUsernameAttributeName() {
        return userDetailsUsernameAttributeName;
    }

    public void setUserDetailsUsernameAttributeName(String userDetailsUsernameAttributeName) {
        this.userDetailsUsernameAttributeName = userDetailsUsernameAttributeName;
    }

    public String getUserDetailsAuthoritiesAttributeName() {
        return userDetailsAuthoritiesAttributeName;
    }

    public void setUserDetailsAuthoritiesAttributeName(String userDetailsAuthoritiesAttributeName) {
        this.userDetailsAuthoritiesAttributeName = userDetailsAuthoritiesAttributeName;
    }
}
