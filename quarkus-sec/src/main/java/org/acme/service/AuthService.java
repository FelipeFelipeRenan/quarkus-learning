package org.acme.service;


import org.acme.model.UserEntity;

import io.quarkus.security.identity.SecurityIdentity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class AuthService {
    @Inject
    private SecurityIdentity securityIdentity;

    @Transactional
    public UserEntity authenticate(String username, String password) {
        // Implemente a autenticação de acordo com sua lógica
        // Aqui estamos apenas retornando um usuário fictício para demonstração
        if ("admin".equals(username) && "admin".equals(password)) {
            UserEntity user = new UserEntity();
            user.setUsername(username);
            return user;
        }
        return null;
    }

    public boolean isUserInRole(String role) {
        return securityIdentity.hasRole(role);
    }

    public String getUserName() {
        return securityIdentity.getPrincipal().getName();
    }
}
