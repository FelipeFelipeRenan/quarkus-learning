package org.acme.repository;

import org.acme.model.UserEntity;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

@ApplicationScoped
public class UserRepository {
    @Inject
    EntityManager entityManager;

    public UserEntity findByUsername(String username){
        return entityManager.createQuery("SELECT u FROM UserEntity u Where u.username = :username", UserEntity.class)
            .setParameter("username", username)
            .getResultList()
            .stream()
            .findFirst()
            .orElse(null);
    }
}
