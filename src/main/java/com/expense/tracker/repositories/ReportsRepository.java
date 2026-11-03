package com.expense.tracker.repositories;

import com.expense.tracker.dtos.*;
import com.expense.tracker.utilities.DBOperations;
import com.expense.tracker.wrappers.MonthlyReportDetailsWrapper;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ReportsRepository {
    private final DBOperations db;

    public ReportsRepository(DBOperations db) {
        this.db = db;
    }

    public List<MonthlyExpenseReportProjection> getMonthlyExpenseReport(SearchCriteria requestCriteria) {
        String sql = "Select g.group_id,g.name group_name,Sum(e.amount) total_expense, " +
                "            Cast((select count(group_member_id) from group_members where group_id = g.group_id AND is_deleted = false) as Integer)total_members " +
                "            from expenses e " +
                "            join groups g on g.group_id = e.group_id and g.is_deleted = false" +
                "            where e.is_deleted = false and Date(e.expense_date) BETWEEN Date(:dateFrom) AND Date(:dateTo) and e.user_id = :userId" +
                "            and (CAST(e.group_id AS VARCHAR) = :groupId or :groupId = '-1')  " +
                "            group by g.group_id,g.name ";

        Map<String, Object> paramMap = new LinkedHashMap<>();
        paramMap.put("userId", requestCriteria.getId());
        for (SearchCriteriaParmeters parameter : requestCriteria.getParameters()) {
            switch (parameter.getParamName()) {
                case "dateFrom" -> paramMap.put("dateFrom", parameter.getParamValue());
                case "dateTo" -> paramMap.put("dateTo", parameter.getParamValue());
                case "groupId" -> paramMap.put("groupId", parameter.getParamValue());

            }
        }

        return db.fetchList(sql, MonthlyExpenseReportProjection.class, paramMap);
    }

    public MonthlyReportDetailsWrapper getMonthlyExpenseReportDetails(SearchCriteria requestCriteria) {
        Map<String, Object> paramMap = new LinkedHashMap<>();
        for (SearchCriteriaParmeters parameter : requestCriteria.getParameters()) {
            switch (parameter.getParamName()) {
                case "dateFrom" -> paramMap.put("dateFrom", parameter.getParamValue());
                case "dateTo" -> paramMap.put("dateTo", parameter.getParamValue());
                case "groupId" -> paramMap.put("groupId", parameter.getParamValue());
            }
        }
        return new MonthlyReportDetailsWrapper(fetchMonthlyExpenseDetails(paramMap), fetchMonthlyExpenseSplitDetails(paramMap));
    }

    private List<MonthlyExpenseDetail> fetchMonthlyExpenseDetails(Map<String, Object> params) {
        String query = """
                SELECT
                    e.expense_id,
                    e.title,
                    e.category,
                    e.expense_date,
                    e.amount                        AS total_amount,
                    e.amount_per_head,
                    gm.name                         AS member_name,
                    ed.is_settled
                FROM expenses e
                JOIN expense_details ed  ON ed.expense_id      = e.expense_id
                JOIN group_members gm    ON gm.group_member_id = ed.group_member_id
                WHERE CAST(e.group_id AS VARCHAR)    = :groupId
                  AND e.is_deleted  = false
                  AND COALESCE (ed.is_deleted,false) = false
                  AND Date(e.expense_date) BETWEEN Date(:dateFrom) AND Date(:dateTo)
                ORDER BY e.expense_date, e.expense_id, gm.name;
                """;
        return db.fetchList(query, MonthlyExpenseDetail.class, params);
    }

    private List<MonthlyExpenseSplitDetails> fetchMonthlyExpenseSplitDetails(Map<String, Object> params) {
        String query = """
                WITH member_balances AS (
                     SELECT
                         gm.group_member_id,
                         gm.name                         AS member_name,
                         gm.email,
                         -- What they are owed by others (they paid on behalf)
                         COALESCE(SUM(ed.amount_to_get), 0) AS total_to_receive,
                         -- What they owe to others
                         COALESCE(SUM(ed.amount_to_pay), 0) AS total_to_pay,
                         -- Net: positive = others owe them, negative = they owe others
                         COALESCE(SUM(ed.amount_to_get), 0)
                             - COALESCE(SUM(ed.amount_to_pay), 0) AS net_balance,
                         -- Unsettled only
                         COALESCE(SUM(CASE WHEN ed.is_settled = false THEN ed.pending_amount ELSE 0 END), 0)
                             AS total_pending
                     FROM group_members gm
                     JOIN expense_details ed ON ed.group_member_id = gm.group_member_id
                     JOIN expenses e          ON e.expense_id       = ed.expense_id
                     WHERE CAST(e.group_id AS VARCHAR)   = :groupId
                       AND e.is_deleted  = false
                       AND COALESCE (ed.is_deleted,false) = false
                       AND Date(e.expense_date) BETWEEN Date(:dateFrom) AND Date(:dateTo)
                     GROUP BY gm.group_member_id, gm.name, gm.email
                 )
                 SELECT
                     member_name,
                     email,
                     total_to_receive,
                     total_to_pay,
                     total_pending,
                     net_balance,
                     CASE
                         WHEN net_balance > 0 THEN 'Gets back ' || net_balance::TEXT
                         WHEN net_balance < 0 THEN 'Owes '      || ABS(net_balance)::TEXT
                         ELSE 'Settled up'
                     END AS final_comments
                 FROM member_balances
                 ORDER BY net_balance DESC;
                """;
        return db.fetchList(query, MonthlyExpenseSplitDetails.class, params);
    }
}

