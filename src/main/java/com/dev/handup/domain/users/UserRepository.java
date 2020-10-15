package com.dev.handup.domain.users;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {

    User findByName(String name);

    List<User> findByEmailAndName(String email, String name);

    Optional<User> findByEmail(String email);


}
