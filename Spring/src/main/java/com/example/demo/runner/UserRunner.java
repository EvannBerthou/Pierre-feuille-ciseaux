package com.example.demo.runner;

import com.example.demo.model.User;
import com.example.demo.model.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserRunner implements CommandLineRunner {
    @Autowired UserRepository userRepository;

    private void addUser(String username, String password) {
        String encodedPassword = new BCryptPasswordEncoder().encode(password);
        User user = new User(username, encodedPassword);
        userRepository.save(user);
    }

    @Override 
    public void run(String...args) throws Exception {
        if (userRepository.count() == 0) {
            addUser("admin", "admin");
            addUser("user", "pass");
        }
    }
}
