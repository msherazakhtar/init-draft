package com.expense.tracker.dtos;

import java.time.LocalDateTime;

public record GroupRecord(Long groupId, String name, Long userId, LocalDateTime createdAt, String createdBy,
        LocalDateTime modifiedAt, String modifiedBy) {

}
