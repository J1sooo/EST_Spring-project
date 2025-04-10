package com.estsoft.demo.user;

import com.estsoft.demo.user.domain.User;
import com.estsoft.demo.user.domain.UserRepository;
import com.estsoft.demo.user.dto.AddUserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService{
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    public void signup(AddUserRequest request) {
        userRepository.save(
                new User(request.getEmail(),
                        encoder.encode(request.getPassword()))
        );
    }
}
