package com.expense.tracker.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class SettlementsSummaryORM {
    @Id
    @Column(name = "expense_settlement_id")
    private Long expenseSettlementId;
    @Column(name = "settlement_date")
    private LocalDate settlementDate;
    @Column(name = "paid_by")
    private String paidBy;
    @Column(name = "paid_to")
    private String paidTo;
    @Column(name = "group_name")
    private String groupName;
    @Column(name = "settlement_amount")
    private BigDecimal settlementAmount;
    @Column(name = "expense_title")
    private String expenseTitle;

    public SettlementsSummaryORM(Long expenseSettlementId, LocalDate settlementDate, String paidBy, String paidTo, String groupName, BigDecimal settlementAmount,String expenseTitle) {
        this.expenseSettlementId = expenseSettlementId;
        this.settlementDate = settlementDate;
        this.paidBy = paidBy;
        this.paidTo = paidTo;
        this.groupName = groupName;
        this.settlementAmount = settlementAmount;
        this.expenseTitle = expenseTitle;
    }

    public SettlementsSummaryORM() {
    }

    public Long getExpenseSettlementId() {
        return expenseSettlementId;
    }

    public void setExpenseSettlementId(Long expenseSettlementId) {
        this.expenseSettlementId = expenseSettlementId;
    }

    public LocalDate getSettlementDate() {
        return settlementDate;
    }

    public void setSettlementDate(LocalDate settlementDate) {
        this.settlementDate = settlementDate;
    }

    public String getPaidBy() {
        return paidBy;
    }

    public void setPaidBy(String paidBy) {
        this.paidBy = paidBy;
    }

    public String getPaidTo() {
        return paidTo;
    }

    public void setPaidTo(String paidTo) {
        this.paidTo = paidTo;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public BigDecimal getSettlementAmount() {
        return settlementAmount;
    }

    public void setSettlementAmount(BigDecimal settlementAmount) {
        this.settlementAmount = settlementAmount;
    }

    public String getExpenseTitle() {
        return expenseTitle;
    }

    public void setExpenseTitle(String expenseTitle) {
        this.expenseTitle = expenseTitle;
    }
}
