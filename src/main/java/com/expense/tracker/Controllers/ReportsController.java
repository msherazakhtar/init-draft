package com.expense.tracker.Controllers;

import com.expense.tracker.dtos.SearchCriteria;
import com.expense.tracker.dtos.MonthlyExpenseReportProjection;
import com.expense.tracker.services.ReportsService;
import com.expense.tracker.wrappers.MonthlyReportDetailsWrapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/reports")
public class ReportsController {
    private final ReportsService reportsService;
    public ReportsController(ReportsService reportsService)
    {
        this.reportsService = reportsService;
    }

    @PostMapping("/monthly")
    public ResponseEntity<List<MonthlyExpenseReportProjection>> getMonthlyExpenseReport(
            @RequestBody SearchCriteria requestCriteria) {
        List<MonthlyExpenseReportProjection> expenseReport = reportsService.getMonthlyExpenseReport(requestCriteria);
        return ResponseEntity.status(HttpStatus.OK).body(expenseReport);

    }
    @PostMapping("/monthly/details")
    public ResponseEntity<MonthlyReportDetailsWrapper> getMonthlyExpenseReportDetails(
            @RequestBody SearchCriteria requestCriteria) {
        MonthlyReportDetailsWrapper reportDetails = reportsService.getMonthlyExpenseReportDetails(requestCriteria);
        return ResponseEntity.status(HttpStatus.OK).body(reportDetails);

    }
}
