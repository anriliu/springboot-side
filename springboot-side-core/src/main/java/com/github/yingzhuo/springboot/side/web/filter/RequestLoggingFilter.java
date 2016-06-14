package com.github.yingzhuo.springboot.side.web.filter;

import com.github.yingzhuo.springboot.side.logging.LoggerBean;
import com.github.yingzhuo.springboot.side.util.IPUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

public class RequestLoggingFilter extends OncePerRequestFilter {

    private LoggerBean loggerBean;

    private static final String BARS = StringUtils.repeat('=', 120);

    private final PathMatcher pathMatcher = new AntPathMatcher();
    private final Set<String> excludes = new HashSet<>();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String uri = request.getRequestURI();

        if (excludes.parallelStream().noneMatch(pattern -> pathMatcher.match(pattern, uri))) {
            doLog(request);
        }

        filterChain.doFilter(request, response);
    }

    private void doLog(HttpServletRequest request) {
        String uri = request.getRequestURI();
        loggerBean.log(BARS);
        loggerBean.log("[URI]: ");
        loggerBean.log("\t\t\t{}", request.getRequestURI());

        loggerBean.log("[METHOD]: ");
        loggerBean.log("\t\t\t{}", request.getMethod());

        loggerBean.log("[CLIENT-IP]: ");
        loggerBean.log("\t\t\t{}", IPUtils.getIpAdress(request));

        loggerBean.log("[HEADERS]: ");
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String name = headerNames.nextElement();
            String value = request.getHeader(name);
            loggerBean.log("\t\t\t{} = {}", name, name.equalsIgnoreCase("cookie") ? StringUtils.abbreviate(value, 60) : value);
        }

        loggerBean.log("[PARAMS]: ");
        Enumeration<String> paramNames = request.getParameterNames();
        while (paramNames.hasMoreElements()) {
            String name = paramNames.nextElement();
            String value = request.getParameter(name);
            loggerBean.log("\t\t\t{} = {}", name, value);
        }

        loggerBean.log(BARS);
    }

    public RequestLoggingFilter addExcludePattern(String pattern) {
        if (pattern != null && !pattern.isEmpty()) {
            excludes.add(pattern);
        }
        return this;
    }

    public RequestLoggingFilter addExcludePatterns(String... patterns) {
        for (String pattern : patterns) {
            addExcludePattern(pattern);
        }
        return this;
    }

    public RequestLoggingFilter clearExcludes() {
        excludes.clear();
        return this;
    }

    public Set<String> getExcludes() {
        return Collections.unmodifiableSet(excludes);
    }

    public LoggerBean getLoggerBean() {
        return loggerBean;
    }

    public void setLoggerBean(LoggerBean loggerBean) {
        this.loggerBean = loggerBean;
    }
}
