package com.blanke.hanu.service.impl;

import com.blanke.hanu.entity.SiteUser;
import com.blanke.hanu.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;


@Service
public class UserInfoService implements UserDetailsService {

    @Autowired
    private UserInfoRepository userInfoRepository;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
       SiteUser customer = userInfoRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User details not found for the user : " + email) );
        Set<GrantedAuthority> listGrantedAuthorities = new HashSet<>();
        return new User(customer.getEmail(), customer.getPassword(), listGrantedAuthorities);
    }


}