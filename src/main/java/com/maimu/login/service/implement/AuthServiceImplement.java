package com.maimu.login.service.implement;

import com.maimu.login.dto.request.auth.IdCheckRequestDto;
import com.maimu.login.dto.request.auth.SignInRequestDto;
import com.maimu.login.dto.request.auth.SignUpRequestDto;
import com.maimu.login.dto.response.ResponseDto;
import com.maimu.login.dto.response.auth.IdCheckResponseDto;
import com.maimu.login.dto.response.auth.SignInResponseDto;
import com.maimu.login.dto.response.auth.SignUpResponseDto;
import com.maimu.login.entity.Users;
import com.maimu.login.provider.JwtProvider;
import com.maimu.login.repository.UsersRepository;
import com.maimu.login.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.security.auth.callback.PasswordCallback;

@Service
@RequiredArgsConstructor
public class AuthServiceImplement implements AuthService {

    private final UsersRepository usersRepository;
    private final JwtProvider jwtProvider;
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Override
    public ResponseEntity<? super IdCheckResponseDto> idCheck(IdCheckRequestDto dto) {
        try{
            String userId = dto.getId();
            boolean isExistId = usersRepository.existsByUserId(userId);
            if(isExistId) return IdCheckResponseDto.duplicateId();
        } catch (Exception exception){
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return IdCheckResponseDto.success();
    }

    @Override
    public ResponseEntity<? super SignUpResponseDto> signUp(SignUpRequestDto dto) {
        try{
            String userId = dto.getId();
            boolean isExistId = usersRepository.existsByUserId(userId);
            if(isExistId) return SignUpResponseDto.duplicatedId();

            String password = dto.getPassword();
            String encodedPassword = passwordEncoder.encode(password);
            dto.setPassword(encodedPassword);

            Users users = new Users(dto);
            usersRepository.save(users);


        } catch(Exception exception){
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return SignUpResponseDto.success();
    }

    @Override
    public ResponseEntity<? super SignInResponseDto> signIn(SignInRequestDto dto) {
        String token = null;

        try{
            String userId = dto.getId();
            Users users = usersRepository.findByUserId(userId);
            if(users == null) SignInResponseDto.signInFail();

            String password = dto.getPassword();
            String encodedPassword = users.getPassword();
            boolean isMatched = passwordEncoder.matches(password, encodedPassword);
            if(!isMatched) return SignInResponseDto.signInFail();

            token = jwtProvider.create(userId);

        } catch(Exception exception){
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return SignInResponseDto.success(token);
    }
}
