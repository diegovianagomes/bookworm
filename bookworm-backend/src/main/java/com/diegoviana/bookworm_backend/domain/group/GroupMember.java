package com.diegoviana.bookworm_backend.domain.group;

import com.diegoviana.bookworm_backend.domain.user.User;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_group_member")
@Data
public class GroupMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer groupmemberId;

    @ManyToOne
    @JoinColumn(
            name = "user_id",
            nullable = false
    )
    private User user;

    @Column(nullable = false)
    private Integer groupId;

    @Column(nullable = false)
    private Boolean role;

    @Column(nullable = false)
    private LocalDateTime createdAt;
}
