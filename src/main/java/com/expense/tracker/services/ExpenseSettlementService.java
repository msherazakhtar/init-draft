package com.expense.tracker.services;

import com.expense.tracker.dtos.ExpenseSettlementRecord;
import com.expense.tracker.dtos.SearchCriteria;
import com.expense.tracker.models.ExpenseSettlementORM;
import com.expense.tracker.models.ExpenseSummaryORM;
import com.expense.tracker.models.SettlementsSummaryORM;

import java.util.List;

public interface ExpenseSettlementService {
    ExpenseSettlementORM expenseSettlementPaid(ExpenseSettlementRecord record);

    List<SettlementsSummaryORM> getSettlementSummary(SearchCriteria requestCriteria);

    // List<ExpenseSettlementORM> getSettlementsByExpenseId(Long expenseId);

    // List<ExpenseSettlementORM> getSettlementsByUserId(Long userId);

    // ExpenseSettlementORM getSettlementById(Long settlementId);

    // String deleteSettlement(Long settlementId);
}
