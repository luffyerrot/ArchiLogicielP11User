package fr.pierre.medheadUser.utils;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class MockUserDetail implements UserDetails {

    private final String username;
    private final String password;
    private final List<GrantedAuthority> authorities;

    public MockUserDetail(String username, String password, String authority) {
        this.username = username;
        this.password = password;
        this.authorities = Collections.singletonList(new MockGrantedAuthority(authority));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }
}
