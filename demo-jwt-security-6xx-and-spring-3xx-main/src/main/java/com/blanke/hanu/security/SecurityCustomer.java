package com.blanke.hanu.security;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.blanke.hanu.entity.SiteUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class SecurityCustomer implements UserDetails {
    private static final long serialVersionUID = -6690946490872875352L;

    private final SiteUser customer;
    public SecurityCustomer(SiteUser customer) {
        this.customer = customer;
    }

    public static SecurityCustomer create(SiteUser user) {
        return new SecurityCustomer(user);
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(""));
        return authorities;
    }

    @Override
    public String getPassword() {
        return customer.getPassword();
    }

    @Override
    public String getUsername() {
        return customer.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
