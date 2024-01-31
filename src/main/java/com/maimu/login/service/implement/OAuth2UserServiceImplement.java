package com.maimu.login.service.implement;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.maimu.login.entity.CustomOAuth2User;
import com.maimu.login.entity.Users;
import com.maimu.login.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class OAuth2UserServiceImplement extends DefaultOAuth2UserService {

    private final UsersRepository usersRepository;
    @Override
    public OAuth2User loadUser(OAuth2UserRequest request) throws OAuth2AuthenticationException{
        OAuth2User oAuth2User = super.loadUser(request);

        String oauthClientName = request.getClientRegistration().getClientName();

        try{
            System.out.println(new ObjectMapper().writeValueAsString(oAuth2User.getAttributes()));
        } catch (Exception exception){
            exception.printStackTrace();
        }

        Users users = new Users();
//        String userId = "aaa";
        String nickname = null;
        String email = null;

        if(oauthClientName.equals("kakao")){

            Map<String, Object> response = (Map<String, Object>) oAuth2User.getAttributes().get("kakao_account");
            Map<String, Object> account = (Map<String, Object>) response.get("profile");
//            userId = "kakao_" + oAuth2User.getAttributes().get("id");
            nickname = (String)account.get("nickname");
            email = (String) (oAuth2User.getAttributes().get("email"));
            users = new Users( nickname, email,"kakao");
        }
        if(oauthClientName.equals("naver")){
            Map<String, Object> response = (Map<String, Object>) oAuth2User.getAttributes().get("response");
//            userId = "naver_" + response.get("id");
            nickname = (String)response.get("nickname");
            email =  (String)response.get("email");
            users = new Users(nickname, email,"naver");
        }
        usersRepository.save(users);

        return new CustomOAuth2User(userId);
    }
}
