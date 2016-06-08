package com.github.yingzhuo.springboot.side.restsec.core;

public interface UserLike {

    public Object getId();

    public String getUsername();

    public String getPassword();

    public Iterable<String> getRoles();

    public Iterable<String> getPermissions();

    public boolean isLocked();

    public boolean isExpired();

}
