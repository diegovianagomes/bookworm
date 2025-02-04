package com.diegoviana.bookworm_backend.domain.action;

import com.diegoviana.bookworm_backend.domain.group.GroupMember;
import com.diegoviana.bookworm_backend.domain.scoring.ScoringMode;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

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
    private String Type;

    @Column(nullable = false)
    private LocalDate createdAt;
}
