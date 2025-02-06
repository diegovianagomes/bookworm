package com.diegoviana.bookworm_backend.infrastructure.web;

import com.diegoviana.bookworm_backend.application.services.UserService;
import com.diegoviana.bookworm_backend.domain.entities.User;
import com.diegoviana.bookworm_backend.domain.exceptions.EntityNotFoundException;
import com.diegoviana.bookworm_backend.domain.exceptions.InvalidDataException;
import com.diegoviana.bookworm_backend.infrastructure.web.dto.UserRequest;
import com.diegoviana.bookworm_backend.infrastructure.web.dto.UserResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest userRequest) {
        try {
            User user = userService.createUser(
                    userRequest.getUserName(),
                    userRequest.getEmail(),
                    userRequest.getPassword()
            );
            // Convert User entity to UserResponse DTO
            UserResponse response = UserResponse.fromEntity(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (InvalidDataException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserResponse> updateUser(
            @PathVariable Integer userId,
            @RequestBody UserRequest userRequest) {
        try {
            User updatedUser = userService.updateUser(
                    userId,
                    userRequest.getUserName(),
                    userRequest.getEmail(),
                    userRequest.getPassword()
            );
            UserResponse response = UserResponse.fromEntity(updatedUser);
            return ResponseEntity.ok(response);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (InvalidDataException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        List<UserResponse> response = users.stream()
                .map(UserResponse::fromEntity)
                .toList();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Integer userId) {
        try {
            User user = userService.getUserById(userId);
            UserResponse response = UserResponse.fromEntity(user);
            return ResponseEntity.ok(response);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUserById(@PathVariable Integer userId) {
        try {
            userService.deleteUserById(userId);
            return ResponseEntity.ok("User deleted successfully");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
