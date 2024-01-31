package com.maimu.login.sociallogin.entity;


import com.maimu.login.sociallogin.dto.request.auth.SignUpRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@Table(name= "users")
public class Users extends BaseTimeEntity {

//    @Id
//    @Column
//    private String userId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="users_id")
    private Long id;

    @Column
    private String nickname;

    @Column
    private String email;

    @Column
    private String type;

    @Column
    private String role;

    public Users(SignUpRequestDto dto){
        this.nickname = "max";
//        this.password = dto.getPassword();
        this.email = dto.getEmail();
        this.type = "app";
        this.role = "ROLE_USER";
    }

    public Users (String nickname, String email, String type){
        this.nickname = nickname;
        this.email = email;
        this.type = type;
        this.role = "ROLE_USER";
    }
}
