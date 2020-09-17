package com.dev.handup.service;

import com.dev.handup.domain.Address;
import com.dev.handup.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.dev.handup.repository.UserRepository;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class UserService {
    private final UserRepository userRepository;

    public User findOne(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User findUserNickname(String nickname) {
        return userRepository.findByNickname(nickname);
    }

    public List<User> findUsers() {
        return userRepository.findAll();
    }

    @Transactional
    public Long join(User user) {
        validateDuplicateMember(user);
        userRepository.save(user);
        return user.getId();
    }

    private void validateDuplicateMember(User user) {
        List<User> findUser = userRepository.findByEmailAndNickname(user.getEmail(), user.getNickname());
        if (!findUser.isEmpty()) {
            throw new IllegalStateException("이미 존재 하는 회원 이메일 또는 닉네임입니다.");
        }
    }

    @Transactional
    public void update(Long id, String password, Address address, String nickname) {
        User user = userRepository.findById(id).orElse(null);

        assert user != null;
        user.setPassword(password);
        user.setAddress(address);
        user.setNickname(nickname);
    }
}
