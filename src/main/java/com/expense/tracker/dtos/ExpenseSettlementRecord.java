package com.expense.tracker.dtos;

import java.math.BigDecimal;
import java.util.Date;

public record ExpenseSettlementRecord(Long expenseSettlementId, Long expenseId, Long payerExpenseDetailsId,
                                      Long receiverExpenseDetailsId, BigDecimal settlementAmount, String settlementType,
                                      Long paidBy, Long paidTo,
                                      Date settlementDate) {

}
