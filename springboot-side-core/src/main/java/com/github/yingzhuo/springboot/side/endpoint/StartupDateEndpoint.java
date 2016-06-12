package com.github.yingzhuo.springboot.side.endpoint;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.actuate.endpoint.AbstractEndpoint;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class StartupDateEndpoint extends AbstractEndpoint<Map<String, Object>> implements ApplicationRunner {

    private Date startup;
    private String pattern = "yyyy-MM-dd HH:mm:ss";

    public StartupDateEndpoint(String id, boolean sensitive, boolean enabled) {
        super(id, sensitive, enabled);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        this.startup = new Date();
    }

    @Override
    public Map<String, Object> invoke() {
        Map<String, Object> json = new HashMap<>();
        json.put("startup", DateFormatUtils.format(startup, pattern));
        json.put("pattern", pattern);
        return json;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

}
