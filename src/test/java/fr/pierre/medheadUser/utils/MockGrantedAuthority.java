package fr.pierre.medheadUser.utils;

import org.springframework.security.core.GrantedAuthority;

public class MockGrantedAuthority implements GrantedAuthority {

    private final String authority;

    public MockGrantedAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return authority;
    }
}
