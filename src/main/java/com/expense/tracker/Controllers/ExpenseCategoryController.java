package com.expense.tracker.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.expense.tracker.dtos.ApiResponse;
import com.expense.tracker.dtos.ExpenseCategoryRecord;
import com.expense.tracker.enums.ResponseStatus;
import com.expense.tracker.models.ExpenseCategoryORM;
import com.expense.tracker.services.ExpenseCategoryService;


@RestController
@RequestMapping("/expenseCategory")
public class ExpenseCategoryController {
    @Autowired
    ExpenseCategoryService expenseCategoryService;

    @PostMapping("/addExpenseCategory")
    public ResponseEntity<ApiResponse<ExpenseCategoryRecord>> addExpenseCategory(@RequestBody ExpenseCategoryRecord expenseCategoryRecord)
    {
         expenseCategoryRecord = expenseCategoryService.addExpenseCategory(expenseCategoryRecord);
        ApiResponse<ExpenseCategoryRecord> apiResponse = new ApiResponse<>(
                "Expense Category Added.",
                expenseCategoryRecord,
                ResponseStatus.SUCCESS);
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }
    @GetMapping("/getExpenseCategories")
    public ResponseEntity<List<ExpenseCategoryRecord>> getExpenseCategories (@RequestParam String userId)
    {
        List<ExpenseCategoryRecord> expenseCategoryRecords = expenseCategoryService.getExpenseCategories(userId);
        return ResponseEntity.status(HttpStatus.OK).body(expenseCategoryRecords);
    }
    @DeleteMapping("/deleteExpenseCategory")
    public ResponseEntity<ApiResponse<String>> deleteExpenseCategory(@RequestBody List<ExpenseCategoryRecord> expenseCategoryRecords)
    {
        String responseMessage = expenseCategoryService.deleteExpenseCategories(expenseCategoryRecords);
         ApiResponse<String> apiResponse = new ApiResponse<>(
                "Expense Category",
                responseMessage,
                ResponseStatus.SUCCESS);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(apiResponse);
    }

    @PutMapping("/updateExpenseCategory")
    public ResponseEntity<ApiResponse<ExpenseCategoryRecord>> updateExpenseCategory(@RequestBody ExpenseCategoryORM expenseCategoryORM)
    {
        // Assuming you have a method in the service to update the category
        ExpenseCategoryRecord updatedCategory = expenseCategoryService.updateExpenseCategory(expenseCategoryORM);
        ApiResponse<ExpenseCategoryRecord> apiResponse = new ApiResponse<>(
                "Expense Category Updated.",
                updatedCategory,
                ResponseStatus.SUCCESS);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }
}
