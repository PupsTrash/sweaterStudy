package com.example.sweaterExp.domain;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER, ADMIN, GOD;

    @Override
    public String getAuthority() {
        return name();
    }
}
