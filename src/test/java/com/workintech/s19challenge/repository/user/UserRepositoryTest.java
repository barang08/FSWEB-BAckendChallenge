package com.workintech.s19challenge.repository.user;

import com.workintech.s19challenge.entity.user.User;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryTest {

    private UserRepository userRepository;

    @Autowired
    public UserRepositoryTest(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private void createUser(String email) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (!optionalUser.isPresent()) {
            User user = new User();
            user.setName("Mehmet");
            user.setEmail("mehmet@gmail.com");
            user.setPassword("123456");
            userRepository.save(user);
        }
    }


    @Test
    @DisplayName("Can find user by email")
    void findByUserEmail() {
        String email = "mehmet@gmail.com";
        createUser(email);
        Optional<User> optionalUser = userRepository.findByEmail(email);
        assertTrue(optionalUser.isPresent());
        User foundUser = optionalUser.get();
        assertEquals("Mehmet", foundUser.getName());
    }


    @Test
    @DisplayName("Can delete user by email")
    @Transactional
    void deleteUserByEmail() {
        String email = "mehmet@gmail.com";
        createUser(email);

        userRepository.deleteByEmail(email);

        Optional<User> deletedUser = userRepository.findByEmail(email);
        assertFalse(deletedUser.isPresent());
    }






}