package com.dev.handup.domain.users;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {

    User findByNickname(String nickname);

    List<User> findByEmailAndNickname(String email, String nickname);

    Optional<User> findByEmail(String email);


}
