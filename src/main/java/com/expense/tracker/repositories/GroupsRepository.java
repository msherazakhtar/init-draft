package com.expense.tracker.repositories;

import java.util.List;

import com.expense.tracker.dtos.projection.GroupSummaryProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.expense.tracker.dtos.GroupRecord;
import com.expense.tracker.models.GroupsORM;

public interface GroupsRepository extends JpaRepository<GroupsORM, Long> {
    @Query(value = """
               SELECT
                   g.group_id,
                   g.name,
                   g.user_id,
                   g.date_created,
                   g.created_by,
                   g.date_modified,
                   g.modified_by,
                   COALESCE(e.total_expense, 0)  AS total_expense,
                   COALESCE(m.total_members, 0)  AS total_members
               FROM groups g
               LEFT JOIN (
                   SELECT group_id, SUM(amount) AS total_expense
                   FROM expenses
                   WHERE is_deleted = false
                   GROUP BY group_id
               ) e ON e.group_id = g.group_id
               LEFT JOIN (
                   SELECT group_id, COUNT(*) AS total_members
                   FROM group_members
                   WHERE is_deleted = false
                   GROUP BY group_id
               ) m ON m.group_id = g.group_id
               WHERE g.user_id = :userId
                 AND g.is_deleted = false
               ORDER BY g.date_created DESC
            """, nativeQuery = true)
    List<GroupSummaryProjection> findGroupsByUserId(Long userId);

}
