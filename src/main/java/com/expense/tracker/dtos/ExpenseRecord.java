package com.expense.tracker.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ExpenseRecord(
        Long id,
        String title,
        String details,
        BigDecimal amount,
        String category,
        Long userId,
        Long groupId,
        String createdBy,
        Boolean isGroup,
        BigDecimal amountPerHead,
        LocalDateTime createdAt,
        LocalDateTime expenseDate) {
}
