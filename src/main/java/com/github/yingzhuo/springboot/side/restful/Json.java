package com.github.yingzhuo.springboot.side.restful;

import org.apache.commons.lang3.tuple.Pair;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Json implements Serializable, Iterable<Pair<String, Object>> {

    private int code;
    private List<String> errors = new ArrayList<>();
    private Map<String, Object> payloads = new HashMap<>();

    public static Json create() {
        return new Json().setCode(HttpStatus.OK);
    }

    public static Json create(HttpStatus httpStatus, String... errors) {
        return new Json().setCode(httpStatus)
                .addErrors(errors);
    }

    private Json() {
        super();
    }

    @Override
    public Iterator<Pair<String, Object>> iterator() {
        List<Pair<String, Object>> list = new ArrayList<>(payloads.size());
        for (String key : payloads.keySet()) {
            Object value = payloads.get(key);
            list.add(Pair.of(key, value));
        }
        return list.iterator();
    }

    @Deprecated
    public Json setCode(int code) {
        this.code = code;
        return this;
    }

    public Json setCode(HttpStatus httpStatus) {
        this.code = httpStatus.value();
        return this;
    }

    public Json addError(String error) {
        this.errors.add(error);
        return this;
    }

    public Json addErrors(String... errors) {
        for (String e : errors) {
            addError(e);
        }
        return this;
    }

    public Json addErrors(Iterable<String> errors) {
        for (String e : errors) {
            addError(e);
        }
        return this;
    }

    public Json addPayload(String key, Object value) {
        this.payloads.put(key, value);
        return this;
    }

    public int getCode() {
        return code;
    }

    public List<String> getErrors() {
        return Collections.unmodifiableList(errors);
    }

    public Map<String, Object> getPayloads() {
        return Collections.unmodifiableMap(payloads);
    }

}
