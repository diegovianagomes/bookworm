package com.diegoviana.bookworm_backend.domain.challenge;

import com.diegoviana.bookworm_backend.domain.scoring.ScoringMode;
import com.diegoviana.bookworm_backend.domain.user.User;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
@Entity
@Table(name = "tb_challenge")
@Data
public class Challenge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int challengeId;

    @Column(nullable = false)
    private String challengeName;

    private String description;

    private String challengeImage;

    @ManyToOne
    @JoinColumn(
            name = "scoring_mode",
            nullable = false
    )
    private ScoringMode scoringMode;

    @Column(nullable = false)
    private LocalDateTime startDate;

    @Column(nullable = false)
    private LocalDateTime endDate;

    @Column(nullable = false)
    private boolean privacySettings;

    @ManyToOne
    @JoinColumn(
            name = "created_by",
            nullable = false
    )
    private User createdBy;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }
}
