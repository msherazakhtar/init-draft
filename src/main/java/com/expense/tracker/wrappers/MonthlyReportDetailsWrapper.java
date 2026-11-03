package com.expense.tracker.wrappers;

import com.expense.tracker.dtos.MonthlyExpenseDetail;
import com.expense.tracker.dtos.MonthlyExpenseSplitDetails;

import java.util.List;

public class MonthlyReportDetailsWrapper {
    List<MonthlyExpenseDetail> lstExpensesDetails;
    List<MonthlyExpenseSplitDetails> lstExpenseSplitDetails;

    public MonthlyReportDetailsWrapper() {
    }

    public MonthlyReportDetailsWrapper(List<MonthlyExpenseDetail> lstExpensesDetails, List<MonthlyExpenseSplitDetails> lstExpenseSplitDetails) {
        this.lstExpensesDetails = lstExpensesDetails;
        this.lstExpenseSplitDetails = lstExpenseSplitDetails;
    }

    public List<MonthlyExpenseDetail> getLstExpensesDetails() {
        return lstExpensesDetails;
    }

    public void setLstExpensesDetails(List<MonthlyExpenseDetail> lstExpensesDetails) {
        this.lstExpensesDetails = lstExpensesDetails;
    }

    public List<MonthlyExpenseSplitDetails> getLstExpenseSplitDetails() {
        return lstExpenseSplitDetails;
    }

    public void setLstExpenseSplitDetails(List<MonthlyExpenseSplitDetails> lstExpenseSplitDetails) {
        this.lstExpenseSplitDetails = lstExpenseSplitDetails;
    }
}
