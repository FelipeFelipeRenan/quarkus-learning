package org.acme.service;

import org.acme.config.JWTConfig;
import org.acme.model.User;
import org.acme.repository.UserRepository;
import org.eclipse.microprofile.jwt.Claims;

import io.smallrye.jwt.build.Jwt;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class AuthService {
    
    @Inject
    private UserRepository repository;

    @Inject
    private JWTConfig config;


    public String generateToken(User user){
        return Jwt.claims()
            .subject(user.getUsername())
            .claim(Claims.upn, user.getUsername())
            .claim("userId", user.getId())
            .expiresIn(config.getExpirationTime())
            .sign(config.getSecretKey());
    }

    public User authenticate(String username, String password) {
        User user = repository.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

}