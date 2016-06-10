package com.github.yingzhuo.springboot.side.endpoint;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.actuate.endpoint.AbstractEndpoint;

import java.util.Calendar;
import java.util.Date;

public class StartupDateEndpoint extends AbstractEndpoint<String> implements InitializingBean {

    private Date startup;
    private String pattern = "yyyy-MM-dd HH:mm:ss";

    public StartupDateEndpoint(String id, boolean sensitive, boolean enabled) {
        super(id, sensitive, enabled);
    }

    @Override
    public String invoke() {
        return DateFormatUtils.format(this.startup, pattern);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.startup = Calendar.getInstance().getTime();
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }
}
