package com.example.demo.config.security;

import com.example.demo.security.TokenAuthProvider;
import com.example.demo.security.TokenSecurityConfigurer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@EnableWebSecurity
public class MultiHttpSecurityConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(MultiHttpSecurityConfig.class);

    @Configuration
    @Order(1)
    public static class InternalApiSecurityConfig extends  WebSecurityConfigurerAdapter {
        @Autowired
        RestAuthenticationEntryPoint restAuthenticationEntryPoint;

        // TODO implement TLS for internal API call
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
                    .antMatchers("/internal/**").permitAll();
        }
    }

    @Configuration
    @Order(2)
    public static class AdminApiSecurityConfig extends WebSecurityConfigurerAdapter {

        @Autowired
        RestAuthenticationEntryPoint restAuthenticationEntryPoint;

        @Autowired
        TokenAuthProvider tokenAuthProvider;

        // TODO implement authentication for admin api
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
                    .antMatchers("/admin/*").permitAll()
                    .antMatchers("/api/**").authenticated()
                    .and()
                    .apply(new TokenSecurityConfigurer(tokenAuthProvider));
        }

    }

    @Configuration
    @Order(3)
    public static class PublicApiSecurityConfig extends WebSecurityConfigurerAdapter {

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
                    .antMatchers("/api/**").authenticated()
                    .and()
                    .apply(new TokenSecurityConfigurer(tokenAuthProvider));
        }

    }
}
