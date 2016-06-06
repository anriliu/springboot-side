package com.github.yingzhuo.springboot.side.qiniuyun.exception;

public class QiniuyunIOException extends RuntimeException {

    public QiniuyunIOException() {
        super();
    }

    public QiniuyunIOException(String message) {
        super(message);
    }

    public QiniuyunIOException(String message, Throwable cause) {
        super(message, cause);
    }

    public QiniuyunIOException(Throwable cause) {
        super(cause);
    }
}
