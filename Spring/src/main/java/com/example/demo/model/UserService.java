package com.example.demo.model;

import java.util.Base64;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired private UserRepository userRepository;
    @Autowired private MyUserDetailsService myUserDetailsService;

    public boolean isValidLogin(User user) {
        UserDetails details = myUserDetailsService.loadUserByUsername(user.getUsername());
        return details.getUsername().equals(user.getUsername()) && 
            new BCryptPasswordEncoder().matches(user.getPassword(), details.getPassword());
    }

    public User getUserLoginFromRequest(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (header == null) {
            return new User();
        }

        String authToken = header.substring("Basic".length()).trim();
        String[] parts = new String(Base64.getDecoder().decode(authToken)).split(":");
        if (parts.length != 2) {
            return new User();
        }

        String tokenUsername = parts[0];
        String tokenPassword = parts[1];
        return new User(tokenUsername, tokenPassword);
    }

    public Long getUserIdFromRequest(HttpServletRequest request) {
        User user = getUserLoginFromRequest(request);
        if (!isValidLogin(user)) {
            return -1L;
        }

        return userRepository.findByUsername(user.getUsername()).getId();
    }
}
