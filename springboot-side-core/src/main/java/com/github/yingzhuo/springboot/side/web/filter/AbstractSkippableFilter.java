package com.github.yingzhuo.springboot.side.web.filter;

import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

public abstract class AbstractSkippableFilter extends OncePerRequestFilter {

    private final PathMatcher pathMatcher = new AntPathMatcher();
    private String[] skipPatterns = new String[0];

    @Override
    protected final void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String requestUri = request.getRequestURI();

        final boolean skip = skipPatterns.length != 0 &&
                Arrays.asList(skipPatterns).parallelStream().anyMatch(pattern -> pathMatcher.match(pattern, requestUri));

        if (!skip) {
            doFilterInternal(request, response);
        }

        filterChain.doFilter(request, response);
    }

    abstract protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

    public void setSkipPatterns(String[] skipPatterns) {
        this.skipPatterns = skipPatterns;
    }
}
