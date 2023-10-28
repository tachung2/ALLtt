package com.tachung.server.service;

import com.tachung.server.domain.user.User;
import com.tachung.server.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthService {
    private final UserRepository userRepository;

    public User Signup(User user) {
        User userEntity = userRepository.save(user);
        return userEntity;
    }
}
