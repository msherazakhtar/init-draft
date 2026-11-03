package com.init.draft.services;

import com.init.draft.events.UserRegisteredEvent;
import com.init.draft.exceptions.InvalidOtpException;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.init.draft.dtos.UserProfileRecord;
import com.init.draft.dtos.UserRecord;
import com.init.draft.exceptions.UserAlreadyExistException;
import com.init.draft.exceptions.UserNotFoundException;
import com.init.draft.models.UsersORM;
import com.init.draft.repositories.UserRepository;
import com.init.draft.utilities.FileUtils;
import com.init.draft.utilities.MappingUtility;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ApplicationEventPublisher eventPublisher;
    private final OtpService otpService;

    public UserServiceImpl(UserRepository userRepository, ApplicationEventPublisher eventPublisher, OtpService otpService) {
        this.userRepository = userRepository;
        this.eventPublisher = eventPublisher;
        this.otpService = otpService;
    }

    @Override
    public UserRecord registerUser(UserRecord user) {

        if (userRepository.existsByEmail(user.email())) {
            throw new UserAlreadyExistException("User with email : " + user.email() + " already exists.");
        }
        UsersORM userORM = MappingUtility.userRecordToORM(user);
        userORM.setPassword(new BCryptPasswordEncoder().encode(userORM.getPassword()));
        userORM.setIsVerified(false);
        userORM = userRepository.save(userORM);
        // publish the event
        eventPublisher.publishEvent(
                new UserRegisteredEvent(this, userORM.getEmail(), userORM.getFirstName() + " " + userORM.getLastName(), otpService.generateAndSaveOtp(userORM.getEmail()))
        );
        return MappingUtility.userORMToUserRecord(userORM);

    }

    @Override
    public String verifyUser(String email, String otp) {
        UsersORM user = userRepository.findByEmail(email)
                .orElseThrow(
                        () -> new UserNotFoundException("User not found..."));
        if (!otpService.verifyOtp(email,otp)) {
            throw new InvalidOtpException("Invalid OTP");
        }
        user.setIsVerified(true);
        userRepository.save(user);
        return "User Verified Successfully.";
    }

    @Override
    public UserProfileRecord getUserProfile(String userId) {
        UsersORM usersORM = userRepository.findById(Long.parseLong(userId)).orElseThrow(
                () -> new UserNotFoundException("User Not Found"));
        return MappingUtility.userORMToUserProfile(usersORM);
    }

    @Override
    public UserProfileRecord updateUserProfile(UserProfileRecord userProfileRecord) {
        UsersORM usersORM = MappingUtility.userProfileRecordToORM(userProfileRecord);
        if (usersORM.getPassword() != null &&
                !usersORM.getPassword().equals("")) {
            usersORM.setPassword(new BCryptPasswordEncoder().encode(usersORM.getPassword()));
        }
        usersORM = userRepository.save(usersORM);
        return MappingUtility.userORMToUserProfile(usersORM);

    }

    @Override
    public UserProfileRecord uploadProfilePicture(String userId, MultipartFile profilePicture) {
        // finding the user by ID
        UsersORM usersORM = userRepository.findById(Long.parseLong(userId)).orElseThrow(
                () -> new UserNotFoundException("User Not Found"));
        // uploading the profile picture and getting the URL
        String profilePictureUrl = FileUtils.uploadFile(profilePicture);
        usersORM.setProfilePictureUrl(profilePictureUrl);
        usersORM = userRepository.save(usersORM);
        return MappingUtility.userORMToUserProfile(usersORM);
    }

}
