package com.service;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public interface IUserSecurity {

    Collection<? extends GrantedAuthority> getAuthorities();

    public String getPassword();

    public String getUsername();
}
