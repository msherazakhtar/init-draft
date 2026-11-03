package com.expense.tracker.services;

import java.util.List;

import com.expense.tracker.dtos.GroupRecord;
import com.expense.tracker.dtos.projection.GroupSummaryProjection;

public interface GroupsService {

    GroupRecord createGroup(GroupRecord groupRecord);

    List<GroupSummaryProjection> getGroupByUserId(Long userId);

    String deleteGroup(Long groupId);

}
