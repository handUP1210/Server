package com.dev.handup.service;

import com.dev.handup.domain.Address;
import com.dev.handup.domain.users.User;
import com.dev.handup.dto.users.UserDto;
import com.dev.handup.domain.users.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    // 조회
    public User findOne(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User findUserNickname(String nickname) {
        return userRepository.findByNickname(nickname);
    }

    public List<User> findUsers() {
        return userRepository.findAll();
    }

    public User findByEmail(String email){
        return userRepository.findByEmail(email).orElse(null);
    }

    private void validateDuplicateMember(User user) {
        List<User> findUser = userRepository.findByEmailAndNickname(user.getEmail(), user.getNickname());
        if (!findUser.isEmpty()) {
            throw new IllegalStateException("이미 존재 하는 회원 이메일 또는 닉네임입니다.");
        }
    }

    public User loginUser(UserDto userDto) throws Exception{

        User user = userRepository.findByEmail(userDto.getEmail()).orElse(null);

        if(user == null) throw new Exception ("멤버가 조회되지 않음");

        if(!user.getPassword().equals(userDto.getPassword()) && !user.getEmail().equals(userDto.getEmail())) {
            throw new Exception("비밀번호 또는 이메일을 확인하세요.");
        }

        return user;
    }

    @Transactional
    public void signUpUser(User user) {
        validateDuplicateMember(user);
        userRepository.save(user);
    }

    @Transactional
    public Long joinUser(UserDto userDto) {
        // 비밀번호 암호화
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        User user = userDto.toEntity();
        return userRepository.save(user).getId();
    }

    @Transactional
    public void update(Long id, String password, Address address, String nickname) {
        User user = userRepository.findById(id).orElse(null);

        // 더티 체킹
        assert user != null;
        user.updateUser(password, nickname, address);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return (UserDetails) userRepository.findByNickname(username);
    }
}
