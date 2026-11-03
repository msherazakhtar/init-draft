package com.expense.tracker.dtos;

import java.math.BigDecimal;

public interface ExpenseDetailsItemView {
    Long getExpenseDetailsId();
    Long getExpenseId();
    Long getGroupMemberId();
    String getGroupMemberName();
    BigDecimal getPaidAmount();
    BigDecimal getAmountToGet();
    BigDecimal getAmountToPay();
    BigDecimal getPendingAmount();
    Boolean getIsSettled();
}
