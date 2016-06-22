package com.github.yingzhuo.springboot.side.restsec.core;

import com.github.yingzhuo.springboot.side.restsec.RestsecConfiguration;
import com.github.yingzhuo.springboot.side.restsec.event.RestsecEventListener;
import com.github.yingzhuo.springboot.side.web.filter.AbstractSkippableFilter;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

public class RestsecFilter extends AbstractSkippableFilter {

    private AccessTokenParser accessTokenParser;
    private UserLikeLoader userLikeLoader;
    private RestsecConfiguration.Mode mode = RestsecConfiguration.Mode.GENERAL;
    private RestsecConfiguration.MockProperties mockProperties;
    private RestsecEventListener eventListener = RestsecEventListener.DEFAULT;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (mode == RestsecConfiguration.Mode.GENERAL) {
            doGeneral(request, response);
        } else {
            doMock(request, response);
        }
    }

    private void doGeneral(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

    private void doMock(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RestsecHolder holder = RestsecHolder.getInstance();
        holder.setAccessToken(new MockAccessToken(mockProperties));
        holder.setUserLike(new MockUserLike(mockProperties));
    }

    private static class MockAccessToken implements UsernamePasswordAccessToken {
        private final RestsecConfiguration.MockProperties mockProperties;

        public MockAccessToken(RestsecConfiguration.MockProperties mockProperties) {
            this.mockProperties = mockProperties;
        }

        @Override
        public String getUsername() {
            return mockProperties.getUsername();
        }

        @Override
        public String getPassword() {
            return mockProperties.getPassword();
        }
    }

    private static class MockUserLike implements UserLike {
        private final RestsecConfiguration.MockProperties mockProperties;

        public MockUserLike(RestsecConfiguration.MockProperties mockProperties) {
            this.mockProperties = mockProperties;
        }

        @Override
        public Object getId() {
            return mockProperties.getId();
        }

        @Override
        public String getUsername() {
            return mockProperties.getUsername();
        }

        @Override
        public String getPassword() {
            return mockProperties.getPassword();
        }

        @Override
        public Iterable<String> getRoles() {
            return Arrays.asList(mockProperties.getRoles());
        }

        @Override
        public Iterable<String> getPermissions() {
            return Arrays.asList(mockProperties.getPermissions());
        }

        @Override
        public boolean isLocked() {
            return mockProperties.isLocked();
        }

        @Override
        public boolean isExpired() {
            return mockProperties.isExpired();
        }

        @Override
        public Object getNativeUser() {
            return null;
        }

        @Override
        public <T> T getNativeUser(Class<T> clas) {
            return null;
        }
    }

    private AccessToken parse(NativeWebRequest webRequest) {
        try {
            eventListener.beforeAccessTokenParsing(webRequest);
            AccessToken accessToken = accessTokenParser.parse(webRequest);
            eventListener.afterAccessTokenParsing(webRequest, accessToken);
            return accessToken;
        } catch (Exception e) {
            return null;
        }
    }

    private UserLike load(AccessToken accessToken) {
        try {
            eventListener.beforeUserLikeLoading(accessToken);
            UserLike userLike =  userLikeLoader.load(accessToken);
            eventListener.afterUserLikeLoading(accessToken, userLike);
            return userLike;
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

    public RestsecConfiguration.Mode getMode() {
        return mode;
    }

    public void setMode(RestsecConfiguration.Mode mode) {
        this.mode = mode;
    }

    public void setMockProperties(RestsecConfiguration.MockProperties mockProperties) {
        this.mockProperties = mockProperties;
    }

    public void setEventListener(RestsecEventListener eventListener) {
        this.eventListener = eventListener;
    }
}
