package com.expense.tracker.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.expense.tracker.dtos.GroupMembersRecord;
import com.expense.tracker.exceptions.ExpenseNotFoundException;
import com.expense.tracker.models.GroupMembersORM;
import com.expense.tracker.repositories.GroupMemberRepository;
import com.expense.tracker.utilities.MappingUtility;

@Service
public class GroupMemberServiceImpl implements GroupMembersService {
    private GroupMemberRepository groupMemberRepository;

    public GroupMemberServiceImpl(GroupMemberRepository groupMemberRepository) {
        this.groupMemberRepository = groupMemberRepository;
    }

    @Override
    public GroupMembersRecord saveGroupMembers(GroupMembersRecord groupMembersRecord) {
        GroupMembersORM groupMemberToSave = groupMemberRepository
                .save(MappingUtility.groupMemebrRecordToORM(groupMembersRecord));
        return MappingUtility.groupMemberORMToRecord(groupMemberToSave);
    }

    @Override
    public List<GroupMembersRecord> getAllGroupMembers(Long groupId) {
        List<GroupMembersORM> groupMembersORM = groupMemberRepository.findByGroupId(groupId);
        List<GroupMembersRecord> groupMembersRecord = new ArrayList<>();
        for (GroupMembersORM groupMemberORM : groupMembersORM) {
            System.out.println(groupMemberORM.getGroupId());
            groupMembersRecord.add(MappingUtility.groupMemberORMToRecord(groupMemberORM));
        }
        return groupMembersRecord;
    }

    @Override
    public String deleteGroupMember(Long groupMemberId) {
        GroupMembersORM groupMemberToDelete = groupMemberRepository.findById(groupMemberId).orElseThrow(
                () -> new ExpenseNotFoundException("Group not found..."));
        groupMemberRepository.delete(groupMemberToDelete);
        return "Group Member Deleted Successfuly";
    }

}
