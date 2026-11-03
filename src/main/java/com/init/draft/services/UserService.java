package com.init.draft.services;

import org.springframework.web.multipart.MultipartFile;

import com.init.draft.dtos.UserProfileRecord;
import com.init.draft.dtos.UserRecord;

public interface UserService {

    UserRecord registerUser(UserRecord user);

    String verifyUser(String email,String otp);

    UserProfileRecord getUserProfile(String userId);

    UserProfileRecord updateUserProfile(UserProfileRecord userProfileRecord);

    UserProfileRecord uploadProfilePicture(String userId, MultipartFile profilePicture);

}
