package com.github.yingzhuo.springboot.side.restful;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@JsonIgnoreProperties({"empty", "notEmpty"})
public class Json implements Serializable, Iterable<String> {

    private static final HttpHeaders JSON_HEADERS = new HttpHeaders();

    private int code;
    private List<String> errors = new ArrayList<>();
    private Map<String, Object> payloads = new HashMap<>();

    static {
        JSON_HEADERS.setContentType(MediaType.APPLICATION_JSON_UTF8);
    }

    public static Json create() {
        return new Json().setCode(HttpStatus.OK);
    }

    public static Json create(HttpStatus httpStatus, String... errors) {
        return new Json().setCode(httpStatus).addErrors(errors);
    }

    private Json() {
        super();
    }

    @Override
    public Iterator<String> iterator() {
        return this.payloads.keySet().iterator();
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

    public Json clear() {
        this.payloads.clear();
        return this;
    }

    public boolean isEmpty() {
        return this.payloads.isEmpty();
    }

    public boolean isNotEmpty() {
        return !isEmpty();
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

    public ResponseEntity<Json> asResponseEntity() {
        return new ResponseEntity<>(this, JSON_HEADERS, HttpStatus.valueOf(getCode()));
    }
}
