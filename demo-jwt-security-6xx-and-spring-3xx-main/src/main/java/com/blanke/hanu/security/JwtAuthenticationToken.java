package com.blanke.hanu.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import java.util.Collection;

@Setter
@Getter
public class JwtAuthenticationToken extends AbstractAuthenticationToken {
        private Long id;
        private String email;
        private String password;

        public JwtAuthenticationToken(Long id, String email, String password) {
            super((Collection)null);
            this.id = id;
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
