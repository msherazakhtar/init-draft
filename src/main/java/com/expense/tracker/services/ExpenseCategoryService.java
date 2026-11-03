package com.expense.tracker.services;

import java.util.List;

import com.expense.tracker.dtos.ExpenseCategoryRecord;
import com.expense.tracker.models.ExpenseCategoryORM;

public interface ExpenseCategoryService {

    ExpenseCategoryRecord addExpenseCategory(ExpenseCategoryRecord expenseCategoryRecord);

    List<ExpenseCategoryRecord> getExpenseCategories(String userId);

    String deleteExpenseCategories(List<ExpenseCategoryRecord> expenseCategoryRecords);

    ExpenseCategoryRecord updateExpenseCategory(ExpenseCategoryORM expenseCategoryORM);


}
