package com.github.yingzhuo.springboot.side.endpoint;

import org.springframework.boot.actuate.endpoint.AbstractEndpoint;

import java.util.UUID;

public class FingerprintEndpoint extends AbstractEndpoint<String> {

    public FingerprintEndpoint(String id, boolean sensitive, boolean enabled) {
        super(id, sensitive, enabled);
    }

    private String fingerprint = UUID.randomUUID().toString();

    @Override
    public String invoke() {
        return fingerprint;
    }
}
