package com.github.yingzhuo.springboot.side.endpoint;

import org.springframework.boot.actuate.endpoint.AbstractEndpoint;
import org.springframework.boot.actuate.endpoint.Endpoint;

import java.util.Collections;
import java.util.List;

public class EndpointsEndpoint extends AbstractEndpoint<List<Endpoint>> {

    private static final String ID = "endpoints";

    private final List<Endpoint> endpoints;
    private boolean sort = true;

    public EndpointsEndpoint(List<Endpoint> endpoints) {
        super(ID);
        this.setEnabled(true);
        this.setSensitive(false);
        this.endpoints = endpoints;
        this.endpoints.add(this);

        if (sort) {
            Collections.sort(this.endpoints, (e1, e2) -> e1.getId().compareTo(e2.getId()));
        }
    }

    @Override
    public List<Endpoint> invoke() {
        return this.endpoints;
    }

    public boolean isSort() {
        return sort;
    }

    public void setSort(boolean sort) {
        this.sort = sort;
    }

}
