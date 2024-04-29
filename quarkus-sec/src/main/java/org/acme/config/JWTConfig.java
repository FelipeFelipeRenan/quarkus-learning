package org.acme.config;


import java.security.PrivateKey;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class JWTConfig {

    @Inject
    @ConfigProperty(name = "mp.jwt.verify.publickey.location")
    PrivateKey secretKey;

    @ConfigProperty(name = "jwt.expiration.time")
    private long expirationTime;

    public PrivateKey getSecretKey() {
        return secretKey;
    }

    public long getExpirationTime() {
        return expirationTime;
    }
    

}
