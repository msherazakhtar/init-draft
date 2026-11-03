package com.expense.tracker.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.expense.tracker.models.GroupMembersORM;

public interface GroupMemberRepository extends JpaRepository<GroupMembersORM, Long> {

    List<GroupMembersORM> findByGroupId(Long groupId);

}
