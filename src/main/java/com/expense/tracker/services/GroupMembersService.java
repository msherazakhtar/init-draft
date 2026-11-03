package com.expense.tracker.services;

import java.util.List;

import com.expense.tracker.dtos.GroupMembersRecord;

public interface GroupMembersService {

    GroupMembersRecord saveGroupMembers(GroupMembersRecord groupMembersRecord);

    List<GroupMembersRecord> getAllGroupMembers(Long groupId);

    String deleteGroupMember(Long groupMemberId);

}
