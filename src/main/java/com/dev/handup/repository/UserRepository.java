package com.dev.handup.repository;

import com.dev.handup.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface UserRepository extends JpaRepository<User, Long> {

    User findByNickname(String nickname);

    List<User> findByEmailAndNickname(String email, String nickname);
}
