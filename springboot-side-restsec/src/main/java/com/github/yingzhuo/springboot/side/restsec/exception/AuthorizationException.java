package com.github.yingzhuo.springboot.side.restsec.exception;

/**
 * 授权异常
 */
public class AuthorizationException extends RestsecException {

    public AuthorizationException() {
        super();
    }

    public AuthorizationException(String message) {
        super(message);
    }

    public AuthorizationException(String message, Throwable cause) {
        super(message, cause);
    }

    public AuthorizationException(Throwable cause) {
        super(cause);
    }
}
