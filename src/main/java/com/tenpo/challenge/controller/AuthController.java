package com.tenpo.challenge.controller;

import com.tenpo.challenge.payload.request.LoginRequest;
import com.tenpo.challenge.payload.request.SignupRequest;
import com.tenpo.challenge.payload.request.TokenRefreshRequest;
import com.tenpo.challenge.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        return authService.authSignin(loginRequest);

    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        return authService.authSignup(signUpRequest);
    }

    @PostMapping("/refreshtoken")
    public ResponseEntity<?> refreshtoken(@Valid @RequestBody TokenRefreshRequest request) {
        return authService.authRefreshToken(request);
    }

    @PostMapping("/signout")
    public ResponseEntity<?> logoutUser() {
        return authService.authSignout();
    }

}

