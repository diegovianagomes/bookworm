package com.diegoviana.bookworm_backend.application.services;

import com.diegoviana.bookworm_backend.domain.entities.User;
import com.diegoviana.bookworm_backend.domain.exceptions.EntityNotFoundException;
import com.diegoviana.bookworm_backend.domain.exceptions.InvalidDataException;
import com.diegoviana.bookworm_backend.domain.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UserServiceUnitTest {
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateUser() throws InvalidDataException {
        // Mock behavior of the repository
        Mockito.when(userRepository.
                findByEmail("diego@gmail.com"))
                .thenReturn(Optional.empty());
        Mockito.when(userRepository.
                save(any(User.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));


        // Call the service method
        User user = userService.createUser("Diego", "diego@gmail.com", "123");

        // Assertions
        assertNotNull(user);
        assertEquals("Diego", user.getUserName());
        assertEquals("diego@gmail.com", user.getEmail());

        // Verify interactions with the repository
        verify(userRepository, times(1)).findByEmail("diego@gmail.com");
        verify(userRepository, times(1)).save(any(User.class));
    }


    @Test
    void testCreateUser_EmailAlreadyExists() {
        Mockito.when(userRepository.findByEmail("diego@gmail.com"))
                .thenReturn(Optional.of(new User()));
        Exception exception = assertThrows(InvalidDataException.class, () ->
                userService.createUser("Diego", "diego@gmail.com", "123"));

        assertEquals("Email already exists", exception.getMessage());

        verify(userRepository, times(1)).findByEmail("diego@gmail.com");
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void testGetUserById() {
        User user = new User();
        user.setUserId(1);
        user.setUserName("Diego");

        Mockito.when(userRepository.findById(1)).thenReturn(Optional.of(user));

        User foundUser = userService.getUserById(1);

        assertNotNull(foundUser);
        assertEquals("Diego", foundUser.getUserName());

        verify(userRepository, times(1)).findById(1);
    }

    @Test
    void testGetUserById_UserNotFound() {
        Mockito.when(userRepository.findById(1)).thenReturn(Optional.empty());

        Exception exception = assertThrows(EntityNotFoundException.class, () ->
                userService.getUserById(1));

        assertEquals("User not found", exception.getMessage());

        verify(userRepository, times(1)).findById(1);
    }

    @Test
    void testDeleteUser() {
        User user = new User();
        user.setUserId(1);

        Mockito.when(userRepository.findById(1)).thenReturn(Optional.of(user));

        userService.deleteUserById(1);

        verify(userRepository, times(1)).findById(1);
        verify(userRepository, times(1)).delete(user);
    }


}
