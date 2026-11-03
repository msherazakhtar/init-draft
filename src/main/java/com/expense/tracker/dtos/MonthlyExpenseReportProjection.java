package com.expense.tracker.dtos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.math.BigDecimal;

@Entity
public class MonthlyExpenseReportProjection {
    @Id
    @Column(name = "group_id")
    Long group_id;
    @Column(name = "group_name")
    String group_name;
    @Column(name = "total_expense")
    BigDecimal total_expense;
    @Column(name = "total_members")
    Integer total_members;

    public Long getGroup_id() {
        return group_id;
    }

    public void setGroup_id(Long group_id) {
        this.group_id = group_id;
    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public BigDecimal getTotal_expense() {
        return total_expense;
    }

    public void setTotal_expense(BigDecimal total_expense) {
        this.total_expense = total_expense;
    }

    public Integer getTotal_members() {
        return total_members;
    }

    public void setTotal_members(Integer total_members) {
        this.total_members = total_members;
    }
}
