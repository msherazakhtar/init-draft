package com.expense.tracker.services;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.expense.tracker.dtos.ExpenseDetailView;
import com.expense.tracker.dtos.ExpenseDetailsItemView;
import com.expense.tracker.dtos.ExpenseDetailsRecord;
import com.expense.tracker.dtos.ExpenseRecord;
import com.expense.tracker.dtos.SearchCriteria;
import com.expense.tracker.dtos.SearchCriteriaParmeters;
import com.expense.tracker.exceptions.ExpenseNotFoundException;
import com.expense.tracker.models.ExpenseDetailsORM;
import com.expense.tracker.models.ExpenseORM;
import com.expense.tracker.models.ExpenseSummaryORM;
import com.expense.tracker.repositories.ExpenseDetailsRepository;
import com.expense.tracker.repositories.ExpenseRepository;
import com.expense.tracker.utilities.MappingUtility;
import com.expense.tracker.wrappers.ExpenseDetailWrapper;
import com.expense.tracker.wrappers.ExpenseWrapper;

import jakarta.transaction.Transactional;

@Service
public class ExpenseServiceImpl implements ExpenseService {
	ExpenseRepository expenseRepository;
	ExpenseDetailsRepository expenseDetailsRepository;

	public ExpenseServiceImpl(ExpenseRepository expenseRepository, ExpenseDetailsRepository expenseDetailsRepository) {
		this.expenseRepository = expenseRepository;
		this.expenseDetailsRepository = expenseDetailsRepository;
	}

	@Override
	@Transactional
	public ExpenseWrapper addExpense(ExpenseWrapper saveExpenseWrapper) {
		ExpenseORM expenseORM = MappingUtility.expenseRecordToORM(saveExpenseWrapper.getExpenseRecord());
		BigDecimal totalAmount = saveExpenseWrapper.getExpenseRecord().amount();
		int memberCount = saveExpenseWrapper.getExpenseDetails().size();
		// Truncate (floor) so per-person shares never exceed the total
		BigDecimal amountPerPerson = totalAmount.divide(BigDecimal.valueOf(memberCount), 2, RoundingMode.DOWN);
		// The last member absorbs any leftover cents (e.g. 800/3 → 266.66 instead of 266.67)
		BigDecimal lastMemberShare = totalAmount.subtract(amountPerPerson.multiply(BigDecimal.valueOf(memberCount - 1)));
		expenseORM.setAmountPerHead(amountPerPerson);
		expenseORM = expenseRepository.save(expenseORM);

		List<ExpenseDetailsRecord> savedExpenseDetails = new ArrayList<>();
		List<ExpenseDetailsRecord> expenseDetailsList = saveExpenseWrapper.getExpenseDetails();
		for (int i = 0; i < expenseDetailsList.size(); i++) {
			ExpenseDetailsRecord expenseDetail = expenseDetailsList.get(i);
			BigDecimal share = (i == expenseDetailsList.size() - 1) ? lastMemberShare : amountPerPerson;
			ExpenseDetailsORM expenseDetailsORM = MappingUtility.expenseDetailsRecordToORM(expenseDetail);
			expenseDetailsORM.setPendingAmount(expenseDetailsORM.getPaidAmount().subtract(share));
			BigDecimal pendingAmount = expenseDetailsORM.getPendingAmount();
			if (pendingAmount.compareTo(BigDecimal.ZERO) == 0) {
				expenseDetailsORM.setAmountToGet(BigDecimal.ZERO);
				expenseDetailsORM.setAmountToPay(BigDecimal.ZERO);
				expenseDetailsORM.setIsSettled(true);
			} else if (pendingAmount.compareTo(BigDecimal.ZERO) > 0) {
				expenseDetailsORM.setAmountToGet(pendingAmount.abs());
				expenseDetailsORM.setIsSettled(false);
			} else if (pendingAmount.compareTo(BigDecimal.ZERO) < 0) {
				expenseDetailsORM.setAmountToPay(pendingAmount.abs());
				expenseDetailsORM.setIsSettled(false);
			}
			expenseDetailsORM.setExpenseId(expenseORM.getId());
			expenseDetailsRepository.save(expenseDetailsORM);
			savedExpenseDetails.add(MappingUtility.expenseDetailsORMToRecord(expenseDetailsORM));
		}

		saveExpenseWrapper.setExpenseRecord(MappingUtility.expenseORMToRecord(expenseORM));
		saveExpenseWrapper.setExpenseDetails(savedExpenseDetails);
		return saveExpenseWrapper;
	}

	@Override
	public List<ExpenseRecord> getExpenseByUserId(SearchCriteria requestCriteria) {
		Sort sort = requestCriteria.getSortingOrder().equalsIgnoreCase("desc")
				? Sort.by(requestCriteria.getSortBy()).descending()
				: Sort.by(requestCriteria.getSortBy()).ascending();

		Page<ExpenseORM> pageExpenses = expenseRepository.findByUserId(requestCriteria.getId(),
				PageRequest.of(requestCriteria.getPageNumber(), requestCriteria.getPageSize(), sort));
		List<ExpenseORM> lstExpenseORM = pageExpenses.getContent();
		List<ExpenseRecord> lstExpenseRecord = new ArrayList<ExpenseRecord>();
		for (ExpenseORM expense : lstExpenseORM) {
			lstExpenseRecord.add(MappingUtility.expenseORMToRecord(expense));
		}
		return lstExpenseRecord;
	}

	@Override
	public List<ExpenseSummaryORM> getExpenseSummary(SearchCriteria requestCriteria) {

		String dateFrom = null;
		String dateTo = null;
		String category = "";
		Integer pageNumber= 1;
		Integer pageSize = 10;
		for (SearchCriteriaParmeters parameter : requestCriteria.getParameters()) {
            switch (parameter.getParamName()) {
                case "dateFrom" -> dateFrom = parameter.getParamValue();
                case "dateTo" -> dateTo = parameter.getParamValue();
                case "category" -> category = parameter.getParamValue();
				case "pageNumber" -> pageNumber = Integer.parseInt(parameter.getParamValue());
				case "pageSize" -> pageSize = Integer.parseInt(parameter.getParamValue());

			}
		}

		return expenseRepository.getUserExpenseSummary(requestCriteria.getId(), category, dateFrom, dateTo,pageNumber,pageSize);
	}

	@Override
	public String deleteSingleExpense(Long expenseId) {
		ExpenseORM expenseToDelete = expenseRepository.findById(expenseId).orElseThrow(
				() -> new ExpenseNotFoundException("Expense not found..."));
		;
		expenseRepository.delete(expenseToDelete);
		return "Expense Deleted Successfuly";

	}

	@Override
	public ExpenseDetailWrapper getExpenseById(Long expenseId) {
		ExpenseDetailView expense = expenseRepository.findExpenseDetailById(expenseId);
		if (expense == null) {
			throw new ExpenseNotFoundException("Expense not found...");
		}
		List<ExpenseDetailsItemView> expenseDetails = expenseDetailsRepository.findExpenseDetailsById(expenseId);
		return new ExpenseDetailWrapper(expense, expenseDetails);
	}

}
