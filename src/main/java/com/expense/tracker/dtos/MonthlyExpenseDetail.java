package com.expense.tracker.dtos;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
@Entity
public class MonthlyExpenseDetail {
    @Id
    private Long expense_id;
    private String title;
    private String category;
    private Timestamp expense_date;
    private BigDecimal total_amount;
    private BigDecimal amount_per_head;
    private String member_name;
    private Boolean is_settled;

    public BigDecimal getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(BigDecimal total_amount) {
        this.total_amount = total_amount;
    }

    public Long getExpense_id() {
        return expense_id;
    }

    public void setExpense_id(Long expense_id) {
        this.expense_id = expense_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Timestamp getExpense_date() {
        return expense_date;
    }

    public void setExpense_date(Timestamp expense_date) {
        this.expense_date = expense_date;
    }

    public BigDecimal getAmount_per_head() {
        return amount_per_head;
    }

    public void setAmount_per_head(BigDecimal amount_per_head) {
        this.amount_per_head = amount_per_head;
    }

    public String getMember_name() {
        return member_name;
    }

    public void setMember_name(String member_name) {
        this.member_name = member_name;
    }

    public Boolean getIs_settled() {
        return is_settled;
    }

    public void setIs_settled(Boolean is_settled) {
        this.is_settled = is_settled;
    }
}
