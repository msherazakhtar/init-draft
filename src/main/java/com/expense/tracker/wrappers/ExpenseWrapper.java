package com.expense.tracker.wrappers;

import java.util.List;

import com.expense.tracker.dtos.ExpenseDetailsRecord;
import com.expense.tracker.dtos.ExpenseRecord;

public class ExpenseWrapper {
    ExpenseRecord expenseRecord;
    List<ExpenseDetailsRecord> expenseDetails;

    public ExpenseWrapper() {
    }

    public ExpenseWrapper(ExpenseRecord expenseRecord, List<ExpenseDetailsRecord> expenseDetails) {
        this.expenseRecord = expenseRecord;
        this.expenseDetails = expenseDetails;
    }

    public ExpenseRecord getExpenseRecord() {
        return expenseRecord;
    }

    public void setExpenseRecord(ExpenseRecord expenseRecord) {
        this.expenseRecord = expenseRecord;
    }

    public List<ExpenseDetailsRecord> getExpenseDetails() {
        return expenseDetails;
    }

    public void setExpenseDetails(List<ExpenseDetailsRecord> expenseDetails) {
        this.expenseDetails = expenseDetails;
    }

}
