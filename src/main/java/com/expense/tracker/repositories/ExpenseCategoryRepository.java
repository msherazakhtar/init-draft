package com.expense.tracker.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.expense.tracker.models.ExpenseCategoryORM;

public interface ExpenseCategoryRepository  extends JpaRepository<ExpenseCategoryORM, Long>{
    List<ExpenseCategoryORM> findAllByUserId(Long userId);
}
