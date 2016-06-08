package com.github.yingzhuo.springboot.side.restsec.core;

import com.github.yingzhuo.springboot.side.web.filter.AbstractSkippableFilter;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RestsecFilter extends AbstractSkippableFilter {

    private AccessTokenParser accessTokenParser;
    private UserLikeLoader userLikeLoader;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        final NativeWebRequest webRequest = new ServletWebRequest(request, response);

        RestsecHolder holder = RestsecHolder.getInstance();
        AccessToken token = parse(webRequest);
        if (token != null) {
            holder.setAccessToken(token);

            UserLike userLike = load(token);
            if (userLike != null) {
                holder.setUserLike(userLike);
            }
        }
    }

    private AccessToken parse(NativeWebRequest webRequest) {
        try {
            return accessTokenParser.parse(webRequest);
        } catch (Exception e) {
            return null;
        }
    }

    private UserLike load(AccessToken accessToken) {
        try {
            return userLikeLoader.load(accessToken);
        } catch (Exception e) {
            return null;
        }
    }

    public void setAccessTokenParser(AccessTokenParser accessTokenParser) {
        this.accessTokenParser = accessTokenParser;
    }

    public void setUserLikeLoader(UserLikeLoader userLikeLoader) {
        this.userLikeLoader = userLikeLoader;
    }
}
