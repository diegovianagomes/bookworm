package com.diegoviana.bookworm_backend.domain.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_action")
@Data
public class Action {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer actionId;

    @ManyToOne
    @JoinColumn(
            name = "groupmember_id",
            nullable = false
    )
    private GroupMember groupMember;

    @ManyToOne
    @JoinColumn(
            name = "scoringmode_id",
            nullable = false
    )
    private ScoringMode scoringMode;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private LocalDateTime createdAt;
}
