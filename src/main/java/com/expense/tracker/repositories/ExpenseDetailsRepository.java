package com.expense.tracker.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.expense.tracker.dtos.ExpenseDetailsItemView;
import com.expense.tracker.models.ExpenseDetailsORM;

public interface ExpenseDetailsRepository extends JpaRepository<ExpenseDetailsORM, Long> {

    List<ExpenseDetailsORM> findByExpenseId(Long expenseId);

    @Query(value = """
            SELECT ed.expense_details_id, ed.expense_id, ed.group_member_id, gm.name AS group_member_name,
            ed.paid_amount, ed.amount_to_get, ed.amount_to_pay, ed.pending_amount, ed.is_settled
            FROM expense_details ed
            JOIN group_members gm ON gm.group_member_id = ed.group_member_id
            WHERE ed.expense_id = :expenseId
            """, nativeQuery = true)
    List<ExpenseDetailsItemView> findExpenseDetailsById(@Param("expenseId") Long expenseId);

}
