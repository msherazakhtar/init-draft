package com.expense.tracker.dtos;

public record ExpenseCategoryRecord(Long categoryId,
        String name,
        Long userId,
        Boolean isGlobal) {

}
