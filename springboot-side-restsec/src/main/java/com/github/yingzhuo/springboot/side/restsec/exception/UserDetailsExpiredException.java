package com.github.yingzhuo.springboot.side.restsec.exception;

/**
 * 用户信息已经过期
 */
public class UserDetailsExpiredException extends AuthenticationException {

    public UserDetailsExpiredException() {
        super();
    }

    public UserDetailsExpiredException(String message) {
        super(message);
    }

    public UserDetailsExpiredException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserDetailsExpiredException(Throwable cause) {
        super(cause);
    }
}
