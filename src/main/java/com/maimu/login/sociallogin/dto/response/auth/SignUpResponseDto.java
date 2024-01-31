package com.maimu.login.sociallogin.dto.response.auth;

import com.maimu.login.sociallogin.common.ResponseCode;
import com.maimu.login.sociallogin.common.ResponseMessage;
import com.maimu.login.sociallogin.dto.response.ResponseDto;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class SignUpResponseDto extends ResponseDto {

    private SignUpResponseDto(){
        super();
    }

    public static ResponseEntity<SignUpResponseDto> success(){
        SignUpResponseDto responseBody = new SignUpResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> duplicatedId(){
        ResponseDto responseBody = new ResponseDto(ResponseCode.DUPLICATE_ID, ResponseMessage.DUPLICATE_ID);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }

//    public static ResponseEntity<ResponseDto> certificationFail(){
//        ResponseDto responseBody = new ResponseDto(ResponseCode.);
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
//    }

}
