package com.diegoviana.bookworm_backend.infrastructure.web.dto;

import com.diegoviana.bookworm_backend.domain.entities.User;
import java.time.LocalDateTime;

public class UserResponse {
    private Integer userId;
    private String userName;
    private String email;
    private LocalDateTime createdAt;


    public static UserResponse fromEntity(User user) {
        UserResponse response = new UserResponse();
        response.setUserId(user.getUserId());
        response.setUserName(user.getUserName());
        response.setEmail(user.getEmail());
        response.setCreatedAt(user.getCreatedAt());
        return response;
    }

    // Getters and setters
    public Integer getUserId() { return userId; }
    public void setUserId(Integer userId) { this.userId = userId; }

    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}