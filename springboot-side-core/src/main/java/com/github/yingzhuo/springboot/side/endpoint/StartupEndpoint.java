package com.github.yingzhuo.springboot.side.endpoint;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.actuate.endpoint.AbstractEndpoint;
import org.springframework.core.env.Environment;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonIgnoreProperties("pattern")
public class StartupEndpoint extends AbstractEndpoint<Map<String, Object>> implements ApplicationRunner {

    private Date startup;
    private String pattern = "yyyy-MM-dd HH:mm:ss";
    private Environment environment;
    private Map<String, List<String>> options = new HashMap<>();

    public StartupEndpoint(String id, boolean sensitive, boolean enabled, Environment environment) {
        super(id, sensitive, enabled);
        this.environment = environment;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        this.startup = new Date();
        for (String optionName : args.getOptionNames()) {
            List<String> optionValue = args.getNonOptionArgs();
            options.put(optionName, optionValue);
        }
    }

    @Override
    public Map<String, Object> invoke() {
        Map<String, Object> json = new HashMap<>();
        json.put("startup", DateFormatUtils.format(startup, pattern));
        json.put("active-profiles", environment.getActiveProfiles());
        json.put("options", options);
        return json;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

}
