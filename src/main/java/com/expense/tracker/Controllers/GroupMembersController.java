package com.expense.tracker.Controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.expense.tracker.dtos.ApiResponse;
import com.expense.tracker.dtos.GroupMembersRecord;
import com.expense.tracker.enums.ResponseStatus;
import com.expense.tracker.services.GroupMembersService;
import com.expense.tracker.utilities.ApiResponseUtil;

@RestController
@RequestMapping("/group-members")
public class
GroupMembersController {
    private GroupMembersService groupMembersService;

    public GroupMembersController(GroupMembersService groupMembersService) {
        this.groupMembersService = groupMembersService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<GroupMembersRecord>> saveGroupMembers(
            @RequestBody GroupMembersRecord groupMembersRecord) {
        groupMembersRecord = groupMembersService.saveGroupMembers(groupMembersRecord);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponseUtil.setApiResponse(ResponseStatus.SUCCESS, groupMembersRecord));

    }

    @GetMapping("/{group-id}")
    public ResponseEntity<List<GroupMembersRecord>> getAllGroupMembers(@PathVariable(name = "group-id") Long groupId) {
        List<GroupMembersRecord> groupMembersRecord = groupMembersService.getAllGroupMembers(groupId);
        return ResponseEntity.status(HttpStatus.OK).body(groupMembersRecord);
    }

    @DeleteMapping
    public ResponseEntity<ApiResponse<String>> deleteGroup(@RequestParam Long groupMemberId) {
        String responseMessage = groupMembersService.deleteGroupMember(groupMemberId);
        ApiResponse<String> apiResponse = new ApiResponse<>(
                "Group",
                responseMessage,
                ResponseStatus.SUCCESS);
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }
}
