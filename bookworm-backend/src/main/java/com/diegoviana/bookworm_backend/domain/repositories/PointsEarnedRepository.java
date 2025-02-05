package com.diegoviana.bookworm_backend.domain.repositories;

import com.diegoviana.bookworm_backend.domain.entities.PointsEarned;

import java.util.List;

public interface PointsEarnedRepository {
    PointsEarned save(PointsEarned pointsEarned);
    List<PointsEarned> findByActionId(Integer actionId);
}
