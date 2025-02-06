package com.diegoviana.bookworm_backend.application.services;

import com.diegoviana.bookworm_backend.domain.entities.User;
import com.diegoviana.bookworm_backend.domain.exceptions.EntityNotFoundException;
import com.diegoviana.bookworm_backend.domain.exceptions.InvalidDataException;
import com.diegoviana.bookworm_backend.domain.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public User createUser(String userName, String email, String password) {
        if (userRepository.findByEmail(email).isPresent()) {
            throw new InvalidDataException("Email already exists");
        }

        User user = new User();
        user.setUserName(userName);
        user.setEmail(email);
        user.setPassword(password); // TODO: Hash the password

        return userRepository.save(user);
    }

    @Transactional
    public User updateUser(Integer userId, String userName, String email, String password) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));


        if (userName != null) {
            user.setUserName(userName);
        }
        if (email != null && !email.equals(user.getEmail())) {
            if (userRepository.findByEmail(email).isPresent()) {
                throw new InvalidDataException("Email already exists");
            }
            user.setEmail(email);
        }
        if (password != null) {
            user.setPassword(password); // TODO: Hash the password
        }

        return userRepository.save(user);
    }


    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Transactional(readOnly = true)
    public User getUserById(Integer userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
    }

    @Transactional
    public void deleteUserById(Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        userRepository.delete(user);
    }
}