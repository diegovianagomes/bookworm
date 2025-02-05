package com.diegoviana.bookworm_backend.domain.repositories;

import com.diegoviana.bookworm_backend.domain.entities.Club;

import java.util.List;
import java.util.Optional;

public interface ClubRepository {
    Club save(Club club);
    Optional<Club> findById(Integer id);
    List<Club> findAll();
    void delete(Club club);
}
