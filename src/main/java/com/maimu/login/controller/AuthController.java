package com.maimu.login.controller;


import com.maimu.login.dto.request.auth.IdCheckRequestDto;
import com.maimu.login.dto.request.auth.SignInRequestDto;
import com.maimu.login.dto.request.auth.SignUpRequestDto;
import com.maimu.login.dto.response.auth.IdCheckResponseDto;
import com.maimu.login.dto.response.auth.SignInResponseDto;
import com.maimu.login.dto.response.auth.SignUpResponseDto;
import com.maimu.login.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/id-check")
    public ResponseEntity<? super IdCheckResponseDto> idCheck (
            @RequestBody @Valid IdCheckRequestDto requestBody)
    {
      ResponseEntity<? super IdCheckResponseDto> response = authService.idCheck(requestBody);
      return response;
    }

    @PostMapping("/sign-up")
    public ResponseEntity<? super SignUpResponseDto> signUp(
            @RequestBody @Valid SignUpRequestDto requestBody
            ){
        ResponseEntity<? super SignUpResponseDto> response = authService.signUp(requestBody);
        return response;
    }

    @PostMapping("/sign-in")
    public ResponseEntity<? super SignInResponseDto> signIn(
            @RequestBody @Valid SignInRequestDto requestBody
            ){
        ResponseEntity<? super SignInResponseDto> response = authService.signIn(requestBody);
        return response;
    }
}
