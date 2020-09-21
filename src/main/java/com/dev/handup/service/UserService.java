package com.dev.handup.service;

import com.dev.handup.domain.Address;
import com.dev.handup.domain.User;
import com.dev.handup.dtos.UserDto;
import com.dev.handup.repository.UserRepository;
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

        // Dirty Check
        assert user != null;
        user.updateUser(password, nickname, address);
    }
    
    public void signUpUser(User user) {
        userRepository.save(user);
    }
    
    public User loginUser(String email, String password) throws Exception{

        User user = userRepository.findByEmail(email);

        if(user == null) throw new Exception ("멤버가 조회되지 않음");

        if(!user.getPassword().equals(password))
            throw new Exception ("비밀번호가 틀립니다.");
        return user;
    }

    @Transactional
    public Long joinUser(UserDto userDto) {
        // 비밀번호 암호화
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));

        return userRepository.save(userDto.toEntity()).getId();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByNickname(username);
    }
}
