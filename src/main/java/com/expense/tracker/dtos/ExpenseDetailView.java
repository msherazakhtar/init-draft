package com.expense.tracker.dtos;

import java.math.BigDecimal;

public interface ExpenseDetailView {
    Long getExpenseId();
    String getTitle();
    String getDetails();
    BigDecimal getTotalAmount();
    String getCategory();
    String getGroupName();
    String getCreatedBy();
    String getExpenseDate();
    BigDecimal getAmountPerHead();
    String getDateCreated();
}