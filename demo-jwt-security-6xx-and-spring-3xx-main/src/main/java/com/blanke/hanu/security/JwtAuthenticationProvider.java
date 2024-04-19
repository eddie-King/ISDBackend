package com.blanke.hanu.security;

import com.blanke.hanu.config.enums.ApiResponseCode;
import com.blanke.hanu.config.exception.RestException;
import com.blanke.hanu.entity.SiteUser;
import com.blanke.hanu.repository.UserInfoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component()
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationProvider implements AuthenticationProvider {
    private final UserInfoRepository userRepository;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        JwtAuthenticationToken authenticationToken = (JwtAuthenticationToken) authentication;
        String username1 = authenticationToken.getPrincipal().toString();
        SiteUser authenticatedUser = userRepository.findByEmail(username1).orElseThrow(() -> new RestException(ApiResponseCode.USER_NOT_EXIST));
//        String pwd = Base64Common.decodeBaseToString(authenticationToken.getPassword());

        //TODO: apply BCrypt
        if (authenticationToken.getPassword().equals(authenticatedUser.getPassword())) {
            //TODO: ....
            return new UsernamePasswordAuthenticationToken(new SecurityCustomer(authenticatedUser), authentication.getCredentials(), null);
        } else {
            throw new RestException(ApiResponseCode.EMAIL_OR_PASSWORD_INVALID);
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JwtAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
