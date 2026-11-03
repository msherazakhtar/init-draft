package com.expense.tracker.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.expense.tracker.dtos.ExpenseDetailView;
import com.expense.tracker.models.ExpenseORM;
import com.expense.tracker.models.ExpenseSummaryORM;

public interface ExpenseRepository extends JpaRepository<ExpenseORM, Long> {

    @Query(value = """
            SELECT e.expense_id, e.title, e.details, e.amount AS total_amount, e.category,
            g.name AS group_name, e.created_by, e.expense_date, e.amount_per_head, e.date_created
            FROM expenses e
            JOIN groups g ON g.group_id = e.group_id
            WHERE e.expense_id = :expenseId
            """, nativeQuery = true)
    ExpenseDetailView findExpenseDetailById(@Param("expenseId") Long expenseId);
    public Page<ExpenseORM> findByUserId(Long userId, Pageable pageable);

    @Query(value = """    
            SELECT e.expense_id,
            e.title,
            e.details,
            e.amount,
            e.category,
            g.name AS group_name,
            e.expense_date,
            CEIL(COUNT(*) OVER()::float /:pageSize) AS total_pages 
            FROM expenses e
            LEFT JOIN groups g ON g.group_id = e.group_id
            WHERE e.user_id = :userId
            AND (:category IS NULL OR :category = '' OR e.category = :category)
            AND DATE(e.expense_date) BETWEEN DATE(:startDate) AND DATE(:endDate)
            AND e.is_deleted = false
            ORDER BY e.date_created DESC
            Limit :pageSize
            OFFSET(:pageNumber - 1) * :pageSize
            """, nativeQuery = true)
    List<ExpenseSummaryORM> getUserExpenseSummary(
            @Param("userId") Long userId,
            @Param("category") String category,
            @Param("startDate") String startDate,
            @Param("endDate") String endDate,
            @Param("pageNumber") Integer pageNumber,
            @Param("pageSize") Integer pageSize

    );


}
