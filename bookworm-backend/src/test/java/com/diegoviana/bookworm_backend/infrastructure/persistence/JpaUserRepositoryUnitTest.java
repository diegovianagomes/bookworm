package com.diegoviana.bookworm_backend.infrastructure.persistence;


import com.diegoviana.bookworm_backend.domain.entities.User;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

public class JpaUserRepositoryUnitTest {
    @Mock
    private EntityManager entityManager;

    @InjectMocks
    private JpaUserRepository userRepository;

    @Test
    void testSaveNewUser() {
        User user = new User();
        Mockito.when(entityManager
                        .merge(any(User.class)))
                        .thenReturn(user);
        User savedUser = userRepository.save(user);
        assertEquals(user, savedUser);
    }
    @Test
    void testFindByEmail() {
        User user = new User();
        user.setEmail("diego@gmail.com");
        Mockito.when(entityManager
                        .createQuery(any(String.class), any(Class.class)))
                        .thenReturn((jakarta.persistence.TypedQuery<User>) Mockito.mock(jakarta.persistence.TypedQuery.class));
        Mockito.when(entityManager
                        .createQuery(any(String.class), any(Class.class)).getResultStream())
                        .thenReturn(java.util.stream.Stream.of(user));

        Optional<User> foundUser = userRepository.findByEmail("diego@gmail.com");

        assertEquals(user, foundUser.orElse(null));
    }

    @Test
    void testFindById() {
        User user = new User();
        user.setUserId(1);
        Mockito.when(entityManager
                .find(User.class, 1))
                .thenReturn(user);

        Optional<User> foundUser = userRepository.findById(1);

        assertEquals(user, foundUser.orElse(null));
    }

}
