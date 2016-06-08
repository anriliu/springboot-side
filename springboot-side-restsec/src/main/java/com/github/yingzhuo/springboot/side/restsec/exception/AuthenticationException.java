package com.github.yingzhuo.springboot.side.restsec.exception;

/**
 * 认证异常
 */
public class AuthenticationException extends RestsecException {

    public AuthenticationException() {
        super();
    }

    public AuthenticationException(String message) {
        super(message);
    }

    public AuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }

    public AuthenticationException(Throwable cause) {
        super(cause);
    }
}
