package com.diegoviana.bookworm_backend.infrastructure.persistence;

import com.diegoviana.bookworm_backend.domain.entities.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Import(JpaUserRepository.class)
public class JpaUserRepositoryUnitIntegrationTest {

    @Autowired
    private JpaUserRepository userRepository;

    @Test
    void testSaveAndFindById() {
        User user = new User();
        user.setUserName("Diego");
        user.setEmail("diego@gmail.com");
        user.setPassword("123");

        userRepository.save(user);

        Optional<User> foundUser = userRepository
                .findById(user.getUserId());
        assertTrue(foundUser.isPresent());
        assertEquals("Diego", foundUser.get().getUserName());
    }

    @Test
    void testDelete() {
        User user = new User();
        user.setUserName("Luigi");
        user.setEmail("luigi@gmail.com");
        user.setPassword("123");

        userRepository.save(user);
        userRepository.delete(user);

        Optional<User> foundUser = userRepository.findById(user.getUserId());
        assertFalse(foundUser.isPresent());
    }
}
