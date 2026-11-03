package com.expense.tracker.exceptions;

public class MailConfigurationNotFoundException extends RuntimeException{
    public MailConfigurationNotFoundException(String message)
    {
        super(message);
    }
}
