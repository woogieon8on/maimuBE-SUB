package com.maimu.login.sociallogin.repository;


import com.maimu.login.sociallogin.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, String> {

    /* 이메일이 Login ID의 역할을 하기 때문에 이메일로 계정 찾는 메소드 구현 */
    Users findByUserId(String userId);

    /* 중복 가입 방지용 */
    boolean existsByUserId(String userId);
}
