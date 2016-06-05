package com.github.yingzhuo.springboot.side.restful.security;

@FunctionalInterface
public interface UserDetailsLoader {

    public UserDetails load(AccessToken accessToken);

}
