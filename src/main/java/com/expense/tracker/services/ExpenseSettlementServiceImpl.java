package com.expense.tracker.services;

import java.math.BigDecimal;
import java.util.List;

import com.expense.tracker.dtos.SearchCriteria;
import com.expense.tracker.dtos.SearchCriteriaParmeters;
import com.expense.tracker.models.ExpenseSummaryORM;
import com.expense.tracker.models.SettlementsSummaryORM;
import org.springframework.stereotype.Service;

import com.expense.tracker.dtos.ExpenseSettlementRecord;
import com.expense.tracker.models.ExpenseDetailsORM;
import com.expense.tracker.models.ExpenseSettlementORM;
import com.expense.tracker.repositories.ExpenseDetailsRepository;
import com.expense.tracker.repositories.ExpenseSettlementRepository;
import com.expense.tracker.utilities.MappingUtility;

import jakarta.transaction.Transactional;

@Service
public class ExpenseSettlementServiceImpl implements ExpenseSettlementService {

    private final ExpenseSettlementRepository settlementRepository;
    private final ExpenseDetailsRepository expenseDetailsRepository;

    public ExpenseSettlementServiceImpl(ExpenseSettlementRepository settlementRepository,
            ExpenseDetailsRepository expenseDetailsRepository) {
        this.settlementRepository = settlementRepository;
        this.expenseDetailsRepository = expenseDetailsRepository;
    }

    @Override
    @Transactional
    public ExpenseSettlementORM expenseSettlementPaid(ExpenseSettlementRecord record) {
        ExpenseDetailsORM payerExpenseDetails = expenseDetailsRepository.findById(record.payerExpenseDetailsId()).orElseThrow();
        ExpenseDetailsORM receiverExpenseDetails = expenseDetailsRepository.findById(record.receiverExpenseDetailsId()).orElseThrow();

        BigDecimal payerSettledAmount = payerExpenseDetails.getAmountToPay().subtract(record.settlementAmount());
        BigDecimal receiverSettledAmount = receiverExpenseDetails.getAmountToGet().subtract(record.settlementAmount());
        payerExpenseDetails.setAmountToPay(payerSettledAmount);
        payerExpenseDetails.setPendingAmount(payerSettledAmount);
        if (payerExpenseDetails.getPendingAmount().compareTo(BigDecimal.ZERO) < 1) {
            payerExpenseDetails.setIsSettled(true);
        }
        expenseDetailsRepository.save(payerExpenseDetails);
        receiverExpenseDetails.setPendingAmount(receiverSettledAmount);
        receiverExpenseDetails.setAmountToGet(receiverSettledAmount);
        if (receiverExpenseDetails.getPendingAmount().compareTo(BigDecimal.ZERO) <1) {
            receiverExpenseDetails.setIsSettled(true);
        }
        expenseDetailsRepository.save(receiverExpenseDetails);
        return settlementRepository.save(MappingUtility.expenseSettlementRecordToORM(record));
    }

    @Override
    public List<SettlementsSummaryORM> getSettlementSummary(SearchCriteria requestCriteria) {
        String dateFrom = null;
        String dateTo = null;
        String category = "";
        for (SearchCriteriaParmeters parameter : requestCriteria.getParameters()) {
            switch (parameter.getParamName()) {
                case "dateFrom" -> dateFrom = parameter.getParamValue();
                case "dateTo" -> dateTo = parameter.getParamValue();
                case "category" -> category = parameter.getParamValue();
            }
        }

        return settlementRepository.getExpenseSettlementSummary(requestCriteria.getId(), dateFrom, dateTo);
    }

    // @Override
    // public List<ExpenseSettlementORM> getSettlementsByExpenseId(Long expenseId) {
    // return settlementRepository.findByExpenseId(expenseId);
    // }

    // @Override
    // public List<ExpenseSettlementORM> getSettlementsByUserId(Long userId) {
    // List<ExpenseSettlementORM> settlements = new ArrayList<>();
    // settlements.addAll(settlementRepository.findBySettledBy(userId));
    // settlements.addAll(settlementRepository.findBySettledTo(userId));
    // return settlements;
    // }

    // @Override
    // public ExpenseSettlementORM getSettlementById(Long settlementId) {
    // return settlementRepository.findById(settlementId).orElse(null);
    // }

    // @Override
    // public String deleteSettlement(Long settlementId) {
    // settlementRepository.deleteById(settlementId);
    // return "Settlement deleted successfully";
    // }
}
