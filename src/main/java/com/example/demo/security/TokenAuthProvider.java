package com.example.demo.security;

import com.auth0.jwt.JWT;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Component
public class TokenAuthProvider {

    public String resolveToken(HttpServletRequest httpRequest) {
        String bearerToken = httpRequest.getHeader(HttpHeaders.AUTHORIZATION);
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7, bearerToken.length());
        }
        return null;
    }

    public Optional<String> validateToken(String token) {
        JWT.decode(token).getClaim("userId").asString();
        // TODO add verify jwt token here
        return Optional.of(JWT.decode(token).getClaim("userId").asString());
    }
}
