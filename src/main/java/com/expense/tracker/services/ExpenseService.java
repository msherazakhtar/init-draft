package com.expense.tracker.services;

import java.util.List;

import com.expense.tracker.dtos.ExpenseRecord;
import com.expense.tracker.dtos.SearchCriteria;
import com.expense.tracker.models.ExpenseSummaryORM;
import com.expense.tracker.wrappers.ExpenseDetailWrapper;
import com.expense.tracker.wrappers.ExpenseWrapper;

public interface ExpenseService {

	ExpenseWrapper addExpense(ExpenseWrapper saveExpenseWrapper);

	List<ExpenseRecord> getExpenseByUserId(SearchCriteria requestCriteria);

	String deleteSingleExpense(Long expenseId);

	List<ExpenseSummaryORM> getExpenseSummary(SearchCriteria requestCriteria);

	ExpenseDetailWrapper getExpenseById(Long expenseId);

}
