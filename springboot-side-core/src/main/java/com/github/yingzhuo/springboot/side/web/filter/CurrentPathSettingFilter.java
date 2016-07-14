package com.github.yingzhuo.springboot.side.web.filter;

import com.github.yingzhuo.springboot.side.web.AttributeScope;
import org.springframework.cglib.core.internal.Function;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CurrentPathSettingFilter extends OncePerRequestFilter {

    private PathGenerator pathGenerator = PathGenerator.DEFAULT;
    private AttributeScope scope = AttributeScope.REQUEST;
    private String currentPathAttributeName = "CURRENT_PATH";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String path = pathGenerator.apply(request);

        if (scope == AttributeScope.REQUEST) {
            request.setAttribute(currentPathAttributeName, path);
        } else {
            request.getSession(true).setAttribute(currentPathAttributeName, path);
        }

        filterChain.doFilter(request, response);
    }

    public static interface PathGenerator extends Function<HttpServletRequest, String> {

        public static final PathGenerator DEFAULT = input -> input.getRequestURI().substring(1).toUpperCase().replaceAll("/", "_");

        public String apply(HttpServletRequest input);

    }

    public PathGenerator getPathGenerator() {
        return pathGenerator;
    }

    public void setPathGenerator(PathGenerator pathGenerator) {
        this.pathGenerator = pathGenerator;
    }

    public AttributeScope getScope() {
        return scope;
    }

    public void setScope(AttributeScope scope) {
        this.scope = scope;
    }

    public String getCurrentPathAttributeName() {
        return currentPathAttributeName;
    }

    public void setCurrentPathAttributeName(String currentPathAttributeName) {
        this.currentPathAttributeName = currentPathAttributeName;
    }

}
