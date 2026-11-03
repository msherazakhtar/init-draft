package com.expense.tracker.services;

public interface OtpService {

    int generateAndSaveOtp(String username);

    boolean verifyOtp(String username, String enteredOtp);
}
