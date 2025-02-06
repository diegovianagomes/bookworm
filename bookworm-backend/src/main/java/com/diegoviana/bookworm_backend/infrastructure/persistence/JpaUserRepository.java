package com.diegoviana.bookworm_backend.infrastructure.persistence;

import com.diegoviana.bookworm_backend.domain.entities.User;
import com.diegoviana.bookworm_backend.domain.repositories.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class JpaUserRepository  implements UserRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User save(User user) {
        if (user.getUserId() == null) {
            entityManager
                    .persist(user);
            return user;
        } else {
            return entityManager
                    .merge(user);
        }
    }

    @Override
    public Optional<User> findById(Integer UserId) {
        return Optional
                .ofNullable(entityManager
                .find(User.class, UserId));
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return entityManager
                .createQuery("SELECT u FROM User u WHERE u.email = :email", User.class)
                .setParameter("email", email)
                .getResultStream()
                .findFirst();
    }

    @Override
    public void delete(User user) {
        entityManager
                .remove(entityManager
                        .contains(user) ? user : entityManager
                        .merge(user));
    }


}
