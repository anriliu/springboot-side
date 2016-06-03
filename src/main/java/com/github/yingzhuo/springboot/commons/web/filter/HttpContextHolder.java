package com.github.yingzhuo.springboot.commons.web.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public final class HttpContextHolder {

    private static final HttpContextHolder INSTANCE = new HttpContextHolder();

    public static HttpContextHolder getInstance() {
        return INSTANCE;
    }

    private static final ThreadLocal<HttpServletRequest> REQUEST_THREAD_LOCAL = new ThreadLocal<>();
    private static final ThreadLocal<HttpServletResponse> RESPONSE_THREAD_LOCAL = new ThreadLocal<>();

    private HttpContextHolder() {
        super();
    }

    public HttpServletRequest getHttpServletRequest() {
        return REQUEST_THREAD_LOCAL.get();
    }

    public HttpServletResponse getHttpServletResponse() {
        return RESPONSE_THREAD_LOCAL.get();
    }

    void setRequest(HttpServletRequest request) {
        REQUEST_THREAD_LOCAL.set(request);
    }

    void setResponse(HttpServletResponse response) {
        RESPONSE_THREAD_LOCAL.set(response);
    }

}
