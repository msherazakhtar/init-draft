package com.expense.tracker.exceptions;

public class ExpenseNotFoundException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ExpenseNotFoundException(String message)
	{
		super(message);
	}
}
