package com.init.draft.services;

import com.init.draft.repositories.OtpCacheRepository;
import com.init.draft.utilities.GeneralUtilities;
import org.springframework.stereotype.Service;

@Service
public class OtpServiceImpl implements OtpService {

    private final OtpCacheRepository otpCacheRepository;
    // private final SmsService smsService; // e.g., Twilio or AWS SNS

    public OtpServiceImpl(OtpCacheRepository otpCacheRepository) {
        this.otpCacheRepository = otpCacheRepository;
    }

    @Override
    public int generateAndSaveOtp(String username) {
        int otp = GeneralUtilities.generateOTP(); // Your OTP generation logic
        // 1. Save to Redis via the repository layer
        otpCacheRepository.save(username, Integer.toString(otp), 15);
        System.out.println("Saved OTP : " + otp);

        return otp;
    }

    @Override
    public boolean verifyOtp(String username, String enteredOtp) {
        String cachedOtp = otpCacheRepository.get(username);
        System.out.println("cachedOtp: " + cachedOtp);
        if (cachedOtp != null && cachedOtp.equals(enteredOtp)) {
            otpCacheRepository.delete(username);
            return true;
        }
        return false;
    }
}