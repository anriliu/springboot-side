package com.github.yingzhuo.springboot.side.restsec.exception;

public abstract class RestsecException extends RuntimeException {

    public RestsecException() {
        super();
    }

    public RestsecException(String message) {
        super(message);
    }

    public RestsecException(String message, Throwable cause) {
        super(message, cause);
    }

    public RestsecException(Throwable cause) {
        super(cause);
    }

}
