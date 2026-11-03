package com.expense.tracker.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.expense.tracker.dtos.ApiResponse;
import com.expense.tracker.dtos.ExpenseRecord;
import com.expense.tracker.dtos.SearchCriteria;
import com.expense.tracker.enums.ResponseStatus;
import com.expense.tracker.models.ExpenseSummaryORM;
import com.expense.tracker.services.ExpenseService;
import com.expense.tracker.wrappers.ExpenseDetailWrapper;
import com.expense.tracker.wrappers.ExpenseWrapper;

@RestController
@RequestMapping("/expense")
public class ExpenseController {
        @Autowired
        ExpenseService expenseService;
        @GetMapping("/sync")
        public  Boolean sync()
        {
                return true;
        }

        @PostMapping
        public ResponseEntity<ApiResponse<ExpenseWrapper>> addExpense(
                        @RequestBody ExpenseWrapper saveExpenseWrapper) {
                ExpenseWrapper expenseWrapper = expenseService.addExpense(saveExpenseWrapper);
                ApiResponse<ExpenseWrapper> apiResponse = new ApiResponse<>(
                                "Expense",
                                expenseWrapper,
                                ResponseStatus.SUCCESS);
                return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);

        }

        @PostMapping("/search")
        public ResponseEntity<List<ExpenseRecord>> getExpgetgeteenseByUserId(
                        @RequestBody SearchCriteria requestCriteria) {
                List<ExpenseRecord> expenseRecord = expenseService.getExpenseByUserId(requestCriteria);
                return ResponseEntity.status(HttpStatus.OK).body(expenseRecord);

        }

        @PostMapping("/summary")
        public ResponseEntity<List<ExpenseSummaryORM>> getExpenseSummary(
                        @RequestBody SearchCriteria requestCriteria) {
                List<ExpenseSummaryORM> expenseSummary = expenseService.getExpenseSummary(requestCriteria);
                return ResponseEntity.status(HttpStatus.OK).body(expenseSummary);

        }

        @GetMapping("/{expenseId}")
        public ResponseEntity<ExpenseDetailWrapper> getExpenseById(
                        @PathVariable("expenseId") Long expenseId) {
                ExpenseDetailWrapper expenseDetailWrapper = expenseService.getExpenseById(expenseId);
                return ResponseEntity.status(HttpStatus.OK).body(expenseDetailWrapper);

        }

        @DeleteMapping("/{expenseId}")
        public ResponseEntity<ApiResponse<String>> deleteSingleExpense(
                        @PathVariable("expenseId") Long expenseId) {
                String responseMessage = expenseService.deleteSingleExpense(expenseId);
                ApiResponse<String> apiResponse = new ApiResponse<>(
                                "Expense",
                                responseMessage,
                                ResponseStatus.SUCCESS);
                return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
        }

}
