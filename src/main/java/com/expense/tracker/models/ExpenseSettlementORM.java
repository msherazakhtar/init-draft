package com.expense.tracker.models;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "expense_settlements")
public class ExpenseSettlementORM extends GlobalFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "expense_settlement_id")
    private Long expenseSettlementId;
    @Column(name = "expense_id")
    private Long expenseId;
    @Column(name = "payer_expense_detail_id")
    private Long payerExpenseDetailsId;
    @Column(name = "receiver_expense_detail_id")
    private Long receiverExpenseDetailsId;
    @Column(name = "settlement_amount")
    private BigDecimal settlementAmount;
    @Column(name = "settlement_type")
    private String settlementType;
    @Column(name = "paid_by")
    private Long paidBy;
    @Column(name = "paid_to")
    private Long paidTo;
    @Column(name = "settlement_date")
    private Date settlementDate;

    public ExpenseSettlementORM(Boolean isDeleted, LocalDateTime createdAt, String createdBy, LocalDateTime modifiedAt,
                                String modifiedBy, Long expenseSettlementId, Long expenseId, Long payerExpenseDetailsId, Long receiverExpenseDetailsId,
                                BigDecimal settlementAmount, String settlementType, Long paidBy, Long paidTo, Date settlementDate) {
        super(isDeleted, createdAt, createdBy, modifiedAt, modifiedBy);
        this.expenseSettlementId = expenseSettlementId;
        this.expenseId = expenseId;
        this.payerExpenseDetailsId = payerExpenseDetailsId;
        this.receiverExpenseDetailsId = receiverExpenseDetailsId;

        this.settlementAmount = settlementAmount;
        this.settlementType = settlementType;
        this.paidBy = paidBy;
        this.paidTo = paidTo;
        this.settlementDate = settlementDate;
    }

    public ExpenseSettlementORM(Long expenseSettlementId, Long expenseId, Long payerExpenseDetailsId, Long receiverExpenseDetailsId,
                                BigDecimal settlementAmount, String settlementType, Long paidBy, Long paidTo, Date settlementDate) {
        this.expenseSettlementId = expenseSettlementId;
        this.expenseId = expenseId;
        this.payerExpenseDetailsId = payerExpenseDetailsId;
        this.receiverExpenseDetailsId = receiverExpenseDetailsId;
        this.settlementAmount = settlementAmount;
        this.settlementType = settlementType;
        this.paidBy = paidBy;
        this.paidTo = paidTo;
        this.settlementDate = settlementDate;
    }

    public ExpenseSettlementORM() {
    }

    public Long getExpenseSettlementId() {
        return expenseSettlementId;
    }

    public void setExpenseSettlementId(Long expenseSettlementId) {
        this.expenseSettlementId = expenseSettlementId;
    }

    public Long getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(Long expenseId) {
        this.expenseId = expenseId;
    }


    public BigDecimal getSettlementAmount() {
        return settlementAmount;
    }

    public void setSettlementAmount(BigDecimal settlementAmount) {
        this.settlementAmount = settlementAmount;
    }

    public String getSettlementType() {
        return settlementType;
    }

    public void setSettlementType(String settlementType) {
        this.settlementType = settlementType;
    }

    public Long getPaidBy() {
        return paidBy;
    }

    public void setPaidBy(Long paidBy) {
        this.paidBy = paidBy;
    }

    public Long getPaidTo() {
        return paidTo;
    }

    public void setPaidTo(Long paidTo) {
        this.paidTo = paidTo;
    }

    public Date getSettlementDate() {
        return settlementDate;
    }

    public void setSettlementDate(Date settlementDate) {
        this.settlementDate = settlementDate;
    }

    public Long getPayerExpenseDetailsId() {
        return payerExpenseDetailsId;
    }

    public void setPayerExpenseDetailsId(Long payerExpenseDetailsId) {
        this.payerExpenseDetailsId = payerExpenseDetailsId;
    }

    public Long getReceiverExpenseDetailsId() {
        return receiverExpenseDetailsId;
    }

    public void setReceiverExpenseDetailsId(Long receiverExpenseDetailsId) {
        this.receiverExpenseDetailsId = receiverExpenseDetailsId;
    }

    @Override
    public String toString() {
        return "ExpenseSettlementORM{" +
                "expenseSettlementId=" + expenseSettlementId +
                ", expenseId=" + expenseId +
                ", payerExpenseDetailsId=" + payerExpenseDetailsId +
                ", receiverExpenseDetailsId=" + receiverExpenseDetailsId +
                ", settlementAmount=" + settlementAmount +
                ", settlementType='" + settlementType + '\'' +
                ", paidBy=" + paidBy +
                ", paidTo=" + paidTo +
                ", settlementDate=" + settlementDate +
                '}';
    }
}
