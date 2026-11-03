package com.expense.tracker.dtos.projection;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface GroupSummaryProjection {
        Long getGroupId();
        String getName();
        Long getUserId();
        LocalDateTime getDateCreated();
        String getCreatedBy();
        LocalDateTime getDateModified();
        String getModifiedBy();
        BigDecimal getTotalExpense();
        Integer getTotalMembers();
    }

