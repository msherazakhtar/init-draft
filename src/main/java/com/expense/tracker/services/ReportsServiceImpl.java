package com.expense.tracker.services;

import com.expense.tracker.dtos.SearchCriteria;
import com.expense.tracker.dtos.MonthlyExpenseReportProjection;
import com.expense.tracker.repositories.ReportsRepository;
import com.expense.tracker.wrappers.MonthlyReportDetailsWrapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportsServiceImpl implements ReportsService {
    private final ReportsRepository reportsRepository;

    public ReportsServiceImpl(ReportsRepository reportsRepository) {
        this.reportsRepository = reportsRepository;
    }

    @Override
    public List<MonthlyExpenseReportProjection> getMonthlyExpenseReport(SearchCriteria requestCriteria) {
        return reportsRepository.getMonthlyExpenseReport(requestCriteria);
    }

    @Override
    public MonthlyReportDetailsWrapper getMonthlyExpenseReportDetails(SearchCriteria requestCriteria) {
        return reportsRepository.getMonthlyExpenseReportDetails(requestCriteria);
    }
}
