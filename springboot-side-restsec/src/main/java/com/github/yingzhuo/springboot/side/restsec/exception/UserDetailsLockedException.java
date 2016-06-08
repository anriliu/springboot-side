package com.github.yingzhuo.springboot.side.restsec.exception;

/**
 * 用户信息已经被锁定 (被禁止登陆)
 */
public class UserDetailsLockedException extends AuthenticationException {

    public UserDetailsLockedException() {
        super();
    }

    public UserDetailsLockedException(String message) {
        super(message);
    }

    public UserDetailsLockedException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserDetailsLockedException(Throwable cause) {
        super(cause);
    }
}
