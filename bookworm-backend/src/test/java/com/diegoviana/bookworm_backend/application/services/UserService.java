package com.diegoviana.bookworm_backend.application.services;

import com.diegoviana.bookworm_backend.domain.entities.User;
import com.diegoviana.bookworm_backend.domain.repositories.UserRepository;
import com.diegoviana.bookworm_backend.domain.exceptions.InvalidDataException;
import jakarta.persistence.EntityNotFoundException;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserService userService;


    public User createUser(String userName, String email, String password) throws InvalidDataException {
        if (userRepository
                .findByEmail(email).isPresent()) {
            throw new InvalidDataException("Email already exists");
        }

        User user = new User();
        user.setUserName(userName);
        user.setEmail(email);
        // TODO:  In the final app, hash the password (202502061115)
        user.setPassword(password);

        return userRepository.save(user);
    }

    public User getUserById(Integer userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
    }

    public void deleteUserById(Integer userId) {
        User user = getUserById(userId);
        userRepository.delete(user);
    }
}
