package com.diegoviana.bookworm_backend.domain.scoring;

import com.diegoviana.bookworm_backend.domain.registration.RegistrationType;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_scoringmode") // Matches the database table name
@Data
public class ScoringMode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer scoringId;

    @Column(nullable = false)
    private String scoringName;

    @ManyToOne
    @JoinColumn(
            name = "scoring_type",
            nullable = false
    )
    private RegistrationType scoringType;


    private String description;
    @Column(nullable = false)
    private LocalDateTime createdAt;

}
