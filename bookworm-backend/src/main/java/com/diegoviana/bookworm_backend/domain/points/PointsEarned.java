package com.diegoviana.bookworm_backend.domain.points;

import com.diegoviana.bookworm_backend.domain.action.Action;
import com.diegoviana.bookworm_backend.domain.scoring.ScoringMode;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_points_earned")
@Data
public class PointsEarned {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pointsearnedId;

    @ManyToOne
    @JoinColumn(
            name = "action_id",
            nullable = false
    )
    private Action action;

    @ManyToOne
    @JoinColumn(
            name = "scoringMode_id",
            nullable = false)
    private ScoringMode scoringMode;

    @Column(
            nullable = false,
            precision = 10,
            scale = 2
    )
    private BigDecimal points;

    @Column(
            nullable = false,
            precision = 5,
            scale = 2
    )
    private BigDecimal multiplier;

    @Column(
            nullable = false,
            precision = 10,
            scale = 2
    )
    private BigDecimal bonusPoints;

    @Column(
            nullable = false,
            precision = 10,
            scale = 2
    )
    private BigDecimal penaltyPoints;

    @Column(
            nullable = false,
            precision = 10,
            scale = 2
    )
    private BigDecimal totalPoints;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    public void calculateTotalPoints() {
        this.totalPoints = this.points
                .multiply(this.bonusPoints)
                .add(this.bonusPoints)
                .subtract(this.penaltyPoints);
    }
}
