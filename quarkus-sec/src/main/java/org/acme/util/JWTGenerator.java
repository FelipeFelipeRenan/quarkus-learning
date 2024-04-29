package org.acme.util;

import org.acme.config.JWTConfig;

import io.smallrye.jwt.build.Jwt;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class JWTGenerator {
    
    @Inject
    private JWTConfig jwtConfig;

    public String generateToken(Long userId, String username){
        return Jwt.builder
    }
}
