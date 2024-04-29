package org.acme.repository;

import org.acme.model.User;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

@ApplicationScoped
public class UserRepository {
    @Inject
    EntityManager entityManager;

    public User findByUsername(String username){
        return entityManager.createQuery("SELECT u FROM User u Where u.username = :username", User.class)
            .setParameter("username", username)
            .getResultList()
            .stream()
            .findFirst()
            .orElse(null);
    }
}
