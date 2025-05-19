package com.estsoft.demo;


import com.estsoft.demo.user.domain.User;
import com.estsoft.demo.user.domain.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;

    @Test
    public void testFindByEmail() {
        // given:
        String email = "mock_email";
        String password = "mock_pw";
        User savedUser = userRepository.save(new User(email, password));

        // when:
        User findUser = userRepository.findByEmail(email).orElseThrow();

        // then:
        assertEquals(savedUser.getId(), findUser.getId());
        assertEquals(savedUser.getEmail(), findUser.getEmail());
        assertEquals(savedUser.getPassword(), findUser.getPassword());
    }

    @Test
    public void testFindAll() {
        // given:
        userRepository.save(new User("mock_email", "mock_pw"));
        userRepository.save(new User("mock_email2", "mock_pw2"));

        // when:
        List<User> userList = userRepository.findAll();

        // then:
        assertEquals(2, userList.size());
    }

    @Test
    public void testSave() {
        // given:
        User user = new User("mock_email", "mock_pw");

        // when:
        User saved = userRepository.save(user);

        // then:
        assertEquals(user.getEmail(), saved.getEmail());
        assertEquals(user.getPassword(), saved.getPassword());
    }

}