package com.blanke.hanu.security;

import com.blanke.hanu.entity.SiteUser;
import com.blanke.hanu.repository.UserInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class HanuUserDetails implements UserDetailsService {

    private final UserInfoRepository userInfoRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<SiteUser> customer = userInfoRepository.findByEmail(email);
        if (customer.isEmpty()) {
            throw new UsernameNotFoundException("User details not found for the user : " + email);
        }
        return new SecurityCustomer(customer.get());
    }

}