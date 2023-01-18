package com.test.task.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@RequiredArgsConstructor
public enum Role implements GrantedAuthority {

    ADMIN("ADMIN"),
    USER("USER"),
    UNAUTHORIZED("UNAUTHORIZED");

    private final String vale;

    @Override
    public String getAuthority() {
        return vale;
    }

}
