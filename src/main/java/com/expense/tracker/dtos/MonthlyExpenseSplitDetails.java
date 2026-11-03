package com.expense.tracker.dtos;

import java.math.BigDecimal;

public class MonthlyExpenseSplitDetails {
    private String member_name;
    private String email;
    private BigDecimal total_to_receive;
    private BigDecimal total_to_pay;
    private BigDecimal total_pending;
    private BigDecimal net_balance;
    private String final_comments;

    public String getMember_name() {
        return member_name;
    }

    public void setMember_name(String member_name) {
        this.member_name = member_name;
    }

    public String getFinal_comments() {
        return final_comments;
    }

    public void setFinal_comments(String final_comments) {
        this.final_comments = final_comments;
    }

    public BigDecimal getNet_balance() {
        return net_balance;
    }

    public void setNet_balance(BigDecimal net_balance) {
        this.net_balance = net_balance;
    }

    public BigDecimal getTotal_pending() {
        return total_pending;
    }

    public void setTotal_pending(BigDecimal total_pending) {
        this.total_pending = total_pending;
    }

    public BigDecimal getTotal_to_pay() {
        return total_to_pay;
    }

    public void setTotal_to_pay(BigDecimal total_to_pay) {
        this.total_to_pay = total_to_pay;
    }

    public BigDecimal getTotal_to_receive() {
        return total_to_receive;
    }

    public void setTotal_to_receive(BigDecimal total_to_receive) {
        this.total_to_receive = total_to_receive;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
