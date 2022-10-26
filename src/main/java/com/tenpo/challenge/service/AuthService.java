package com.tenpo.challenge.service;

import com.tenpo.challenge.payload.request.LoginRequest;
import com.tenpo.challenge.payload.request.SignupRequest;
import com.tenpo.challenge.payload.request.TokenRefreshRequest;
import org.springframework.http.ResponseEntity;

public interface AuthService {

    ResponseEntity<?> authSignin(LoginRequest loginRequest);
    ResponseEntity<?> authSignup(SignupRequest signUpRequest);
    ResponseEntity<?> authRefreshToken(TokenRefreshRequest request);
    ResponseEntity<?> authSignout();
}
