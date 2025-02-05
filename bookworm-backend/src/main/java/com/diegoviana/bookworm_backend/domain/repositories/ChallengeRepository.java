package com.diegoviana.bookworm_backend.domain.repositories;

import com.diegoviana.bookworm_backend.domain.entities.Challenge;

import java.util.List;
import java.util.Optional;

public interface ChallengeRepository {
    Challenge save(Challenge challenge);
    Optional<Challenge> findById(Integer id);
    List<Challenge> findAll();
    void delete(Challenge challenge);
}
