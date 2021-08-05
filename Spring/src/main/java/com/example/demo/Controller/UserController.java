package com.example.demo.Controller;

import java.security.Principal;
import java.util.Base64;

import javax.servlet.http.HttpServletRequest;

import com.example.demo.model.MyUserDetailsService;
import com.example.demo.model.User;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class UserController {
    @RequestMapping("/login")
    public boolean login(@RequestBody User user) {
        UserDetails details = new MyUserDetailsService().loadUserByUsername(user.getUsername());
        return details.getUsername().equals(user.getUsername()) && 
            new BCryptPasswordEncoder().matches(user.getPassword(), details.getPassword());
    }

    @RequestMapping("/user")
    public Principal user(HttpServletRequest request) {
		String authToken = request.getHeader("Authorization").substring("Basic".length()).trim();
		return () -> new String(Base64.getDecoder().decode(authToken)).split(":")[0];
    }
}
