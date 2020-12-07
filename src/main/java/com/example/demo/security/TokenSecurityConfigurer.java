package com.example.demo.security;

import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


public class TokenSecurityConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private TokenAuthProvider tokenAuthProvider;

    public TokenSecurityConfigurer(TokenAuthProvider tokenAuthProvider) {
        this.tokenAuthProvider = tokenAuthProvider;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(new TokenAuthFilter(this.tokenAuthProvider), UsernamePasswordAuthenticationFilter.class);
    }
}
