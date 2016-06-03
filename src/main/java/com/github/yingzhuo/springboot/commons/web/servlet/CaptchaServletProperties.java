package com.github.yingzhuo.springboot.commons.web.servlet;


import com.github.yingzhuo.springboot.commons.web.config.AbstractServletProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "springboot.side.captcha-servlet")
public class CaptchaServletProperties extends AbstractServletProperties {

    private boolean enabled = false;
    private int width = 100;
    private int height = 18;
    private String sessionAttributeName = CaptchaServlet.class.getName();

    public CaptchaServletProperties() {
        super.setEnabled(false);
        super.setServletName(CaptchaServlet.class.getName());
        super.setUrlMappings(new String[] {CaptchaServlet.class.getSimpleName()});
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getSessionAttributeName() {
        return sessionAttributeName;
    }

    public void setSessionAttributeName(String sessionAttributeName) {
        this.sessionAttributeName = sessionAttributeName;
    }
}
