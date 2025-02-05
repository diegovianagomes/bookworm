package com.diegoviana.bookworm_backend.domain.repositories;

import com.diegoviana.bookworm_backend.domain.entities.User;
import java.util.Optional;

public interface UserRepository {
    User save(User user);
    Optional<User> findById(Integer UserId);
    Optional<User> findByEmail(String email);
    void delete(User user);
}
