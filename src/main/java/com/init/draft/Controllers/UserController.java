package com.init.draft.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.init.draft.dtos.ApiResponse;
import com.init.draft.dtos.UserProfileRecord;
import com.init.draft.dtos.UserRecord;
import com.init.draft.enums.ResponseStatus;
import com.init.draft.services.UserService;
import com.init.draft.utilities.ApiResponseUtil;

@RestController
@RequestMapping("/user")
public class UserController {
        UserService userService;

        public UserController(UserService userService) {
                this.userService = userService;
        }

        @GetMapping("/sayHello")
        public String sayHello() {
                return "Hello Developer...";
        }

        @PostMapping("/register")
        public ResponseEntity<ApiResponse<UserRecord>> registerUser(@RequestBody UserRecord userRecord) {
                userRecord = userService.registerUser(userRecord);
                return ResponseEntity.status(HttpStatus.CREATED)
                                .body(ApiResponseUtil.setApiResponse(ResponseStatus.SUCCESS, userRecord));
        }

        @PostMapping("/verify")
        public ResponseEntity<ApiResponse<String>> verifyUserEmail(
                        @RequestParam(name = "email") String email,
                        @RequestParam(name = "otp") String otp

        ) {
                System.out.println(email);
                System.out.println(otp);
                String respMessage = userService.verifyUser(email, otp);
                ApiResponse<String> apiResponse = new ApiResponse<>(
                                "User Verification",
                                respMessage,
                                ResponseStatus.SUCCESS);
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(apiResponse);
        }

        @GetMapping("/{userId}")
        public ResponseEntity<UserProfileRecord> getUserProfile(
                        @PathVariable(name = "userId") String userId) {
                UserProfileRecord userProfileRecord = userService.getUserProfile(userId);

                return ResponseEntity.status(HttpStatus.OK).body(userProfileRecord);
        }

        @PutMapping
        public ResponseEntity<UserProfileRecord> updateUserProfile(
                        @RequestBody UserProfileRecord userProfileRecord) {
                UserProfileRecord userProfileRecordUpdated = userService.updateUserProfile(userProfileRecord);

                return ResponseEntity.status(HttpStatus.OK).body(userProfileRecordUpdated);
        }

        @PutMapping("/profile-picture/{userId}")
        public ResponseEntity<UserProfileRecord> uploadProfilePicture(
                        @RequestParam(name = "userId") String userId,
                        @RequestParam("profilePicture") MultipartFile profilePicture) {
                UserProfileRecord userProfileRecordUpdated = userService.uploadProfilePicture(userId, profilePicture);
                return ResponseEntity.status(HttpStatus.OK).body(userProfileRecordUpdated);
        }

}
