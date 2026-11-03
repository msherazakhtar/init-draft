package com.expense.tracker.repositories;

import com.expense.tracker.models.ExpenseSummaryORM;
import com.expense.tracker.models.SettlementsSummaryORM;
import org.springframework.data.jpa.repository.JpaRepository;

import com.expense.tracker.models.ExpenseSettlementORM;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ExpenseSettlementRepository extends JpaRepository<ExpenseSettlementORM, Long> {




    @Query(value = """
            Select distinct es.expense_settlement_id,es.settlement_date,mp.name paid_by,
            mr.name paid_to,g.name group_name,es.settlement_amount,e.title expense_title
            from expense_settlements es
            join group_members mp on mp.group_member_id = es.paid_by
            join group_members mr on mr.group_member_id = es.paid_to
            join groups g on g.group_id = mp.group_id
            join expenses e on e.expense_id = es.expense_id
            where Date(es.settlement_date) between date(:startDate) and date(:endDate)
            and coalesce(es.is_deleted,false) = false and g.user_id = :userId
            order by es.settlement_date desc
            """, nativeQuery = true)
    List<SettlementsSummaryORM> getExpenseSettlementSummary(
            @Param("userId") Long userId,
            @Param("startDate") String startDate,
            @Param("endDate") String endDate);
    // List<ExpenseSettlementORM> findByExpenseId(Long expenseId);

    // List<ExpenseSettlementORM> findBySettledBy(Long settledBy);

    // List<ExpenseSettlementORM> findBySettledTo(Long settledTo);
}
