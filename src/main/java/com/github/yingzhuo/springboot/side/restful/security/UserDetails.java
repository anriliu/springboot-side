package com.github.yingzhuo.springboot.side.restful.security;

import java.io.Serializable;
import java.util.Collection;

public interface UserDetails extends Serializable {

    public Object getNativeUser();

    public String username();

    public String password();

    public Collection<String> getRoles();

    public Collection<String> getPermissions();

}
