package com.diegoviana.bookworm_backend.domain.club;

import com.diegoviana.bookworm_backend.domain.scoring.ScoringMode;
import com.diegoviana.bookworm_backend.domain.user.User;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_club")
@Data
public class Club {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer clubId;

    @Column(nullable = false)
    private String clubName;

    private String description;

    private String clubImage;

    @ManyToOne
    @JoinColumn(
            name = "scoring_mode",
            nullable = false
    )
    private ScoringMode scoringMode;

    @Column(nullable = false)
    private boolean privacySettings;

    @ManyToOne
    @JoinColumn(
            name = "created_by",
            nullable = false
    )
    private User user;

    @Column(nullable = false)
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
