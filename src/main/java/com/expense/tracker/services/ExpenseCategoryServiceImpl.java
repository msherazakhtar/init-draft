package com.expense.tracker.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.expense.tracker.dtos.ExpenseCategoryRecord;
import com.expense.tracker.models.ExpenseCategoryORM;
import com.expense.tracker.repositories.ExpenseCategoryRepository;
import com.expense.tracker.utilities.MappingUtility;

@Service
public class ExpenseCategoryServiceImpl implements ExpenseCategoryService {
    ExpenseCategoryRepository expenseCategoryRepository;

    public ExpenseCategoryServiceImpl(ExpenseCategoryRepository expenseCategoryRepository) {
        this.expenseCategoryRepository = expenseCategoryRepository;
    }

    @Override
    public ExpenseCategoryRecord addExpenseCategory(ExpenseCategoryRecord expenseCategoryRecord) {
        ExpenseCategoryORM expenseCategoryORM = MappingUtility.expenseRecordToCategoryORM(expenseCategoryRecord);
        expenseCategoryORM = expenseCategoryRepository.save(expenseCategoryORM);
        return MappingUtility.expenseCategoryORMToRecord(expenseCategoryORM);
    }

    @Override
    public List<ExpenseCategoryRecord> getExpenseCategories(String userId) {
        List<ExpenseCategoryORM> expenseCategoryORMs = expenseCategoryRepository
                .findAllByUserId(Long.parseLong(userId));
        List<ExpenseCategoryRecord> expenseCategoryRecords = new ArrayList<>();
        for (ExpenseCategoryORM category : expenseCategoryORMs) {
            expenseCategoryRecords.add(MappingUtility.expenseCategoryORMToRecord(category));
        }
        return expenseCategoryRecords;
    }

    @Override
    public String deleteExpenseCategories(List<ExpenseCategoryRecord> expenseCategoryRecords) {
        List<ExpenseCategoryORM> expenseCategoryORMs = new ArrayList<>();
        for (ExpenseCategoryRecord record : expenseCategoryRecords) {
            expenseCategoryORMs.add(MappingUtility.expenseRecordToCategoryORM(record));
        }
        expenseCategoryRepository.deleteAll(expenseCategoryORMs);
        return "Categories Deleted";

    }

    @Override
    public ExpenseCategoryRecord updateExpenseCategory(ExpenseCategoryORM expenseCategoryORM) {
        ExpenseCategoryORM updatedCategory = expenseCategoryRepository.save(expenseCategoryORM);
        return MappingUtility.expenseCategoryORMToRecord(updatedCategory);
    }

}
