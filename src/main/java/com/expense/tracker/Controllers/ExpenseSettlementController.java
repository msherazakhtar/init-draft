package com.expense.tracker.Controllers;

import com.expense.tracker.dtos.SearchCriteria;
import com.expense.tracker.models.ExpenseSummaryORM;
import com.expense.tracker.models.SettlementsSummaryORM;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.expense.tracker.dtos.ApiResponse;
import com.expense.tracker.dtos.ExpenseSettlementRecord;
import com.expense.tracker.enums.ResponseStatus;
import com.expense.tracker.models.ExpenseSettlementORM;
import com.expense.tracker.services.ExpenseSettlementService;

import java.util.List;

@RestController
@RequestMapping("/settlement")
public class ExpenseSettlementController {

    private final ExpenseSettlementService settlementService;

    public ExpenseSettlementController(ExpenseSettlementService settlementService) {
        this.settlementService = settlementService;
    }

    @PostMapping("/paid")
    public ResponseEntity<ApiResponse<ExpenseSettlementORM>> expenseSettlementPaid(
            @RequestBody ExpenseSettlementRecord record) {
        ExpenseSettlementORM savedSettlement = settlementService.expenseSettlementPaid(record);
        ApiResponse<ExpenseSettlementORM> apiResponse = new ApiResponse<>(
                "Settlement",
                savedSettlement,
                ResponseStatus.SUCCESS);
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    @PostMapping("/summary")
    public ResponseEntity<List<SettlementsSummaryORM>> getSettlementSummary(
            @RequestBody SearchCriteria requestCriteria) {
        List<SettlementsSummaryORM> expenseSummary = settlementService.getSettlementSummary(requestCriteria);
        return ResponseEntity.status(HttpStatus.OK).body(expenseSummary);

    }

    // @GetMapping("/user/{userId}")
    // public ResponseEntity<List<ExpenseSettlementORM>> getSettlementsByUserId(
    // @PathVariable Long userId) {
    // List<ExpenseSettlementORM> settlements =
    // settlementService.getSettlementsByUserId(userId);
    // return ResponseEntity.status(HttpStatus.OK).body(settlements);
    // }

    // @GetMapping("/{settlementId}")
    // public ResponseEntity<ExpenseSettlementORM> getSettlementById(
    // @PathVariable Long settlementId) {
    // ExpenseSettlementORM settlement =
    // settlementService.getSettlementById(settlementId);
    // return ResponseEntity.status(HttpStatus.OK).body(settlement);
    // }

    // @DeleteMapping("/{settlementId}")
    // public ResponseEntity<ApiResponse<String>> deleteSettlement(
    // @PathVariable Long settlementId) {
    // String responseMessage = settlementService.deleteSettlement(settlementId);
    // ApiResponse<String> apiResponse = new ApiResponse<>(
    // "Settlement",
    // responseMessage,
    // ResponseStatus.SUCCESS);
    // return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    // }
}
