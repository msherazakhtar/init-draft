package com.expense.tracker.services;

import java.util.List;

import com.expense.tracker.dtos.projection.GroupSummaryProjection;
import org.springframework.stereotype.Service;

import com.expense.tracker.dtos.GroupRecord;
import com.expense.tracker.exceptions.ExpenseNotFoundException;
import com.expense.tracker.models.GroupsORM;
import com.expense.tracker.repositories.GroupsRepository;
import com.expense.tracker.utilities.MappingUtility;

@Service
public class GroupsServiceImpl implements GroupsService {
    private GroupsRepository groupsRepository;

    public GroupsServiceImpl(GroupsRepository groupsRepository) {
        this.groupsRepository = groupsRepository;
    }

    @Override
    public GroupRecord createGroup(GroupRecord groupRecord) {
        GroupsORM groupORM = MappingUtility.groupRecordToORM(groupRecord);
        groupORM = groupsRepository.save(groupORM);
        return MappingUtility.groupORMToRecord(groupORM);

    }

    @Override
    public List<GroupSummaryProjection> getGroupByUserId(Long userId) {
        System.out.println("execute grou by id");
        return groupsRepository.findGroupsByUserId(userId);
        // List<GroupsORM> groupsList = groupsRepository.findGroupsByUserId(userId);
        // List<GroupRecord> groupRecords = new ArrayList<>();
        // for (GroupsORM group : groupsList) {
        // groupRecords.add(MappingUtility.groupORMToRecord(group));
        // }

        // return groupRecords;
    }

    @Override
    public String deleteGroup(Long groupId) {
        GroupsORM groupToDelete = groupsRepository.findById(groupId).orElseThrow(
                () -> new ExpenseNotFoundException("Group not found..."));
        ;
        groupsRepository.delete(groupToDelete);
        return "Group Deleted Successfuly";
    }

}
