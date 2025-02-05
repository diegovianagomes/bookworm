package com.diegoviana.bookworm_backend.domain.repositories;

import com.diegoviana.bookworm_backend.domain.entities.GroupMember;

import java.util.List;
import java.util.Optional;

public interface GroupMemberRepository {
    GroupMember save(GroupMember groupMember);
    Optional<GroupMember> findById(Integer groupmemberId);
    List<GroupMember> findByGroupId(Integer groupId);
    void delete(GroupMember groupMember);
}
