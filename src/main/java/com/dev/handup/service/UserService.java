package com.dev.handup.service;

import com.dev.handup.domain.Address;
import com.dev.handup.domain.users.User;
import com.dev.handup.domain.users.UserRepository;
import com.dev.handup.dto.users.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class UserService {

    private final UserRepository userRepository;

    private void validateDuplicateMember(User user) {
        List<User> findUser = userRepository.findByEmailAndName(user.getEmail(), user.getName());
        if (!findUser.isEmpty()) {
            throw new IllegalStateException("이미 존재 하는 회원 이메일 또는 닉네임입니다.");
        }
    }

    // 조회
    public User findOne(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User findUserName(String name) {
        return userRepository.findByName(name);
    }

    public List<User> findUsers() {
        return userRepository.findAll();
    }

    public User findByEmail(String email){
        return userRepository.findByEmail(email).orElse(null);
    }

    public void loginUser(UserDto userDto) throws Exception{

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));

        User user = userRepository.findByEmail(userDto.getEmail()).orElse(null);

        if(user == null) throw new Exception ("멤버가 조회되지 않음");

        if(!user.getPassword().equals(userDto.getPassword()) && !user.getEmail().equals(userDto.getEmail())) {
            throw new Exception("비밀번호 또는 이메일을 확인하세요.");
        }
    }

    @Transactional
    public Long joinUser(UserDto userDto) {
        // 비밀번호 암호화
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));

        // NOT_PERMITTED 권한 상태
        User user = userDto.toEntity();

        // 중복 체크
        validateDuplicateMember(user);

        return userRepository.save(user).getId();
    }

    @Transactional
    public void update(Long id, String password, String name) {
        User user = userRepository.findById(id).orElse(null);

        // 더티 체킹
        assert user != null;
        user.updateUser(password, name);
    }
}
