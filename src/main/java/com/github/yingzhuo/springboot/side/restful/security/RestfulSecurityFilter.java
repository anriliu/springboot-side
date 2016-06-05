package com.github.yingzhuo.springboot.side.restful.security;

import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RestfulSecurityFilter extends OncePerRequestFilter {

    private AccessTokenParser accessTokenParser;
    private UserDetailsLoader userDetailsLoader;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final NativeWebRequest nativeWebRequest = new ServletWebRequest(request, response);

        AccessToken accessToken = accessTokenParser.parse(nativeWebRequest);

        if (accessToken != null) {
            AccessTokenHolder.getInstance().set(accessToken);

            UserDetails userDetails = userDetailsLoader.load(accessToken);
            if (userDetails != null) {
                UserDetailsHolder.getInstance().set(userDetails);
            }
        }

        filterChain.doFilter(request, response);
    }

    public void setAccessTokenParser(AccessTokenParser accessTokenParser) {
        this.accessTokenParser = accessTokenParser;
    }

    public void setUserDetailsLoader(UserDetailsLoader userDetailsLoader) {
        this.userDetailsLoader = userDetailsLoader;
    }

}
