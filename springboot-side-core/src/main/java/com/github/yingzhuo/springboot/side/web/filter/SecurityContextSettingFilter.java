package com.github.yingzhuo.springboot.side.web.filter;

import com.github.yingzhuo.springboot.side.web.AttributeScope;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.stream.Collectors;

public class SecurityContextSettingFilter extends OncePerRequestFilter {

    private AttributeScope scope = AttributeScope.REQUEST;
    private String userDetailsAttributeName = "USER_DETAILS";
    private String userDetailsUsernameAttributeName = "USER_DETAILS_USERNAME";
    private String userDetailsAuthoritiesAttributeName = "USER_DETAILS_AUTHORITIES";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        final SecurityContext sc = SecurityContextHolder.getContext();

        try {
            final UserDetails userDetails = (UserDetails) sc.getAuthentication().getPrincipal();
            final String username = userDetails.getUsername();
            final Collection<String> authorities = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());

            if (scope == AttributeScope.REQUEST) {
                request.setAttribute(userDetailsAttributeName, userDetails);
                request.setAttribute(userDetailsUsernameAttributeName, username);
                request.setAttribute(userDetailsAuthoritiesAttributeName, authorities);
            } else {
                request.getSession(true).setAttribute(userDetailsAttributeName, userDetails);
                request.getSession(true).setAttribute(userDetailsUsernameAttributeName, username);
                request.getSession(true).setAttribute(userDetailsAuthoritiesAttributeName, authorities);
            }

        } catch (Exception ignored) {
            // NOP
        }

        filterChain.doFilter(request, response);
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
