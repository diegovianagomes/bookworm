package com.diegoviana.bookworm_backend.domain.repositories;

import com.diegoviana.bookworm_backend.domain.entities.ScoringMode;

import java.util.List;
import java.util.Optional;

public interface ScoringModeRepository {
    ScoringMode save(ScoringMode scoringMode);
    Optional<ScoringMode> findById(Integer id);
    List<ScoringMode> findAll();
}
