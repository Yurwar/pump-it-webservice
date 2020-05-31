package com.pumpit.webservice.model.entity;

import org.springframework.security.core.GrantedAuthority;

public enum Authority implements GrantedAuthority {
    CLIENT,
    TRAINER;

    @Override
    public String getAuthority() {
        return name();
    }
}
