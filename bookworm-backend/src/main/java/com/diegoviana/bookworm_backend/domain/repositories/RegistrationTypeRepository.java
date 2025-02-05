package com.diegoviana.bookworm_backend.domain.repositories;

import com.diegoviana.bookworm_backend.domain.entities.RegistrationType;

import java.util.List;
import java.util.Optional;

public interface RegistrationTypeRepository {
    RegistrationType save(RegistrationType registrationType);
    Optional<RegistrationType> findById(Integer registrationId);
    List<RegistrationType> findAll();
}
