package com.diegoviana.bookworm_backend.domain.user;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDateTime;

@Table(name = "tb_user")
@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    private String userName;
    private String email;
    private String password;
    private String userPicture;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
