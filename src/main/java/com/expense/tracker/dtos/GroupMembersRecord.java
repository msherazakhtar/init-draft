package com.expense.tracker.dtos;

import java.time.LocalDateTime;

public record GroupMembersRecord(Long groupMemebrId, String name, String email, String phone, Long groupId,
                LocalDateTime createdAt,
                String createdBy, LocalDateTime modifiedAt, String modifiedBy) {

}
