package com.blanke.hanu.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import java.util.Collection;

@Setter
@Getter
public class JwtAuthenticationToken extends AbstractAuthenticationToken {
        private String email;
        private String password;

        public JwtAuthenticationToken(String email, String password) {
            super((Collection)null);
            this.password =  password;
            this.email = email;
            this.setAuthenticated(false);
        }

        @Override
        public Object getCredentials() {
            return email;
        }

        @Override
        public Object getPrincipal() {
            return email;
        }

}
