package com.example.demo.Controller;

import java.util.Base64;

import javax.servlet.http.HttpServletRequest;

import com.example.demo.model.MyUserDetailsService;
import com.example.demo.model.Profile;
import com.example.demo.model.User;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class UserController {

    private boolean isValidLogin(String username, String password) {
        UserDetails details = new MyUserDetailsService().loadUserByUsername(username);
        return details.getUsername().equals(username) && 
            new BCryptPasswordEncoder().matches(password, details.getPassword());
    }

    @PostMapping("/login")
    public boolean login(@RequestBody User user) {
        return isValidLogin(user.getUsername(), user.getPassword());
    }

    //TODO: Vérifier qu'un authToken est présent dans la requête
    @GetMapping("/user/{username}")
    public Profile user(HttpServletRequest request, @PathVariable String username) {
		String authToken = request.getHeader("Authorization").substring("Basic".length()).trim();
		String[] parts = new String(Base64.getDecoder().decode(authToken)).split(":");
        String tokenUsername = parts[0];
        String tokenPassword = parts[1];

        // Si l'utilisateur connecté veut regarder son propre profil
        if (username.equals(tokenUsername) && isValidLogin(username, tokenPassword)) {
            return new Profile(username);
        } 

        return new Profile(username);
    }
}
