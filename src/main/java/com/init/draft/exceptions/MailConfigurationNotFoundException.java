package com.init.draft.exceptions;

public class MailConfigurationNotFoundException extends RuntimeException{
    public MailConfigurationNotFoundException(String message)
    {
        super(message);
    }
}
