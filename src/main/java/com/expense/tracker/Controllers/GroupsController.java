package com.expense.tracker.Controllers;

import java.util.List;

import com.expense.tracker.dtos.projection.GroupSummaryProjection;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.expense.tracker.dtos.ApiResponse;
import com.expense.tracker.dtos.GroupRecord;
import com.expense.tracker.enums.ResponseStatus;
import com.expense.tracker.services.GroupsService;
import com.expense.tracker.utilities.ApiResponseUtil;

@RestController
@RequestMapping("/groups")
public class GroupsController {
    GroupsService groupsService;

    public GroupsController(GroupsService groupsService) {
        this.groupsService = groupsService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<GroupRecord>> createGroup(@RequestBody GroupRecord groupRecord) {
        groupRecord = groupsService.createGroup(groupRecord);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponseUtil.setApiResponse(ResponseStatus.SUCCESS, groupRecord));
    }

    @GetMapping
    public ResponseEntity<List<GroupSummaryProjection>> getGroupByUserId(@RequestParam Long userId) {
        List<GroupSummaryProjection> groupRecord = groupsService.getGroupByUserId(userId);
        return ResponseEntity.status(HttpStatus.OK).body(groupRecord);
    }

    @DeleteMapping
    public ResponseEntity<ApiResponse<String>> deleteGroup(@RequestParam Long groupId) {
        String responseMessage = groupsService.deleteGroup(groupId);
        ApiResponse<String> apiResponse = new ApiResponse<>(
                "Group",
                responseMessage,
                ResponseStatus.SUCCESS);
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

}
