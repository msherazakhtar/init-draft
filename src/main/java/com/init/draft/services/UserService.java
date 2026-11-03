package com.expense.tracker.services;

import org.springframework.web.multipart.MultipartFile;

import com.expense.tracker.dtos.UserProfileRecord;
import com.expense.tracker.dtos.UserRecord;

public interface UserService {

    UserRecord registerUser(UserRecord user);

    String verifyUser(String email,String otp);

    UserProfileRecord getUserProfile(String userId);

    UserProfileRecord updateUserProfile(UserProfileRecord userProfileRecord);

    UserProfileRecord uploadProfilePicture(String userId, MultipartFile profilePicture);

}
