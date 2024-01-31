package com.maimu.login.sociallogin.service;

import com.maimu.login.sociallogin.dto.request.auth.IdCheckRequestDto;
import com.maimu.login.sociallogin.dto.request.auth.SignInRequestDto;
import com.maimu.login.sociallogin.dto.request.auth.SignUpRequestDto;
import com.maimu.login.sociallogin.dto.response.auth.IdCheckResponseDto;
import com.maimu.login.sociallogin.dto.response.auth.SignInResponseDto;
import com.maimu.login.sociallogin.dto.response.auth.SignUpResponseDto;
import org.springframework.http.ResponseEntity;

public interface AuthService {

    ResponseEntity<? super IdCheckResponseDto> idCheck(IdCheckRequestDto dto);
    ResponseEntity<? super SignUpResponseDto> signUp(SignUpRequestDto dto);
    ResponseEntity<? super SignInResponseDto> signIn(SignInRequestDto dto);
}
