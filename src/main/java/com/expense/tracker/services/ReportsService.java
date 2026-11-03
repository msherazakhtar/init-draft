package com.expense.tracker.services;

import com.expense.tracker.dtos.SearchCriteria;
import com.expense.tracker.dtos.MonthlyExpenseReportProjection;
import com.expense.tracker.wrappers.MonthlyReportDetailsWrapper;

import java.util.List;

public interface ReportsService {
    List<MonthlyExpenseReportProjection> getMonthlyExpenseReport(SearchCriteria requestCriteria);

    MonthlyReportDetailsWrapper getMonthlyExpenseReportDetails(SearchCriteria requestCriteria);
}
