package com.init.draft.services;

public interface OtpService {

    int generateAndSaveOtp(String username);

    boolean verifyOtp(String username, String enteredOtp);
}
