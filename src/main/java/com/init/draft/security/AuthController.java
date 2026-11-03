package com.init.draft.security;

import java.util.Map;

import com.init.draft.events.UserRegisteredEvent;
import com.init.draft.exceptions.UserNotFoundException;
import com.init.draft.models.UsersORM;
import com.init.draft.repositories.UserRepository;
import com.init.draft.services.OtpRateLimiterService;
import com.init.draft.services.OtpService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import com.init.draft.dtos.ApiResponse;
import com.init.draft.dtos.AuthRequest;
import com.init.draft.dtos.UserRecord;
import com.init.draft.enums.ResponseStatus;
import com.init.draft.services.UserService;
import com.init.draft.utilities.ApiResponseUtil;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private AuthenticationManager authenticationManager;

    private final JwtUtil jwtUtil;

    private final UserService userService;
    private final OtpService otpService;
    private final UserRepository userRepository;
    private final ApplicationEventPublisher eventPublisher;
    private final OtpRateLimiterService otpRateLimiterService;


    //    private UserRepository userRepo;
    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil,
                          UserService userService, OtpService otpService, UserRepository userRepository,
                          ApplicationEventPublisher eventPublisher, OtpRateLimiterService otpRateLimiterService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userService = userService;
        this.otpService = otpService;
        this.userRepository = userRepository;
        this.eventPublisher = eventPublisher;
        this.otpRateLimiterService = otpRateLimiterService;
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody AuthRequest authRequest) {
        try {

            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));

            UserDetails user = (UserDetails) auth.getPrincipal();
            String token = jwtUtil.generateToken(user);

            return ResponseEntity.ok(Map.of("token", token));
        } catch (DisabledException ex) {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body(Map.of("Error", "Your email is not verified. Please verify your email via OTP."));
        } catch (BadCredentialsException | UsernameNotFoundException ex) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("Error", "Invalid Username or Password"));
        }
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<UserRecord>> registerUser(@RequestBody UserRecord user) {
        user = userService.registerUser(user);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponseUtil.setApiResponse(ResponseStatus.SUCCESS, user));
    }

    @PostMapping("/generateNewOtp")
    public ResponseEntity<ApiResponse<String>> generateNewOtp(@RequestParam("email") String email) {
        if (!otpRateLimiterService.isAllowed(email)) {
            long waitTime = otpRateLimiterService.getRemainingTimeMinutes(email);
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS)
                    .body(ApiResponseUtil.setApiResponse(ResponseStatus.ERROR, "You have requested too many OTPs. Please try again in " + waitTime + " minutes."));
        }
        UsersORM usersORM = userRepository.findByEmail(email).orElseThrow(
                () -> new UserNotFoundException("User Not Found"));
        int otp = otpService.generateAndSaveOtp(email);
        eventPublisher.publishEvent(
                new UserRegisteredEvent(this, usersORM.getEmail(), usersORM.getFirstName() + " " + usersORM.getLastName(), otp)
        );

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponseUtil.setApiResponse(ResponseStatus.SUCCESS, "New OTP has been sent to " + email));
    }

//     @PostMapping("/register")
//     public ResponseEntity<String> register(@RequestBody AuthRequest request) {
//     UsersORM user = new UsersORM();
//     user.setEmail(request.getUsername());
//     user.setPassword(new BCryptPasswordEncoder().encode(request.getPassword()));
//     // user.setRole("ROLE_USER");
//     userRepo.save(user);
//     return ResponseEntity.ok("User registered");
//     }
}
