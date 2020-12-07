package com.example.demo.config.security;

import com.example.demo.security.TokenAuthProvider;
import com.example.demo.security.TokenSecurityConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    @Autowired
    TokenAuthProvider tokenAuthProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .cors()
        .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
            .httpBasic().disable()
            .csrf().disable()
            .formLogin().disable()
            .exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint)
        .and()
            .authorizeRequests()
            .antMatchers("/actuator/*").permitAll()
            .antMatchers("/**").authenticated()
        .and()
            .apply(new TokenSecurityConfigurer(tokenAuthProvider));
    }
}
