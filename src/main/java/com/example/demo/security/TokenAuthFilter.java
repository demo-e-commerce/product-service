package com.example.demo.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Collections;
import java.util.Optional;

public class TokenAuthFilter extends GenericFilterBean {

    private TokenAuthProvider tokenAuthProvider;

    public TokenAuthFilter(TokenAuthProvider tokenAuthProvider) {
        this.tokenAuthProvider = tokenAuthProvider;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String token = tokenAuthProvider.resolveToken((HttpServletRequest) request);
        if (token != null) {
            Optional<String> userId = tokenAuthProvider.validateToken(token);
            if (userId.isPresent()) {
                User user = new User(userId.get());
                SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(
                        user,
                        token,
                        Collections.emptyList()
                ));
            }
        }
        chain.doFilter(request, response);
    }

}
