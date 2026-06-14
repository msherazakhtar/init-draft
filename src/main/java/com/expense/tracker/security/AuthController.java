package com.expense.tracker.security;

import java.util.Map;

import com.expense.tracker.models.UsersORM;
import com.expense.tracker.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.expense.tracker.dtos.ApiResponse;
import com.expense.tracker.dtos.AuthRequest;
import com.expense.tracker.dtos.UserRecord;
import com.expense.tracker.enums.ResponseStatus;
import com.expense.tracker.services.UserService;
import com.expense.tracker.utilities.ApiResponseUtil;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private AuthenticationManager authenticationManager;

    private JwtUtil jwtUtil;

    private UserService userService;

//    private UserRepository userRepo;
    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil,
            UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody AuthRequest authRequest) {
        try {

            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));

            UserDetails user = (UserDetails) auth.getPrincipal();
            String token = jwtUtil.generateToken(user);

            return ResponseEntity.ok(Map.of("token", token));
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
