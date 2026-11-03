package com.expense.tracker.wrappers;

import java.util.List;

import com.expense.tracker.dtos.ExpenseDetailView;
import com.expense.tracker.dtos.ExpenseDetailsItemView;

public class ExpenseDetailWrapper {
    private ExpenseDetailView expense;
    private List<ExpenseDetailsItemView> expenseDetails;

    public ExpenseDetailWrapper(ExpenseDetailView expense, List<ExpenseDetailsItemView> expenseDetails) {
        this.expense = expense;
        this.expenseDetails = expenseDetails;
    }

    public ExpenseDetailView getExpense() {
        return expense;
    }

    public List<ExpenseDetailsItemView> getExpenseDetails() {
        return expenseDetails;
    }
}