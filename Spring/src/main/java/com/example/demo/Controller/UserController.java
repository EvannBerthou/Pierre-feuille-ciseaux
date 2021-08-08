package com.example.demo.Controller;

import javax.servlet.http.HttpServletRequest;

import com.example.demo.model.Profil;
import com.example.demo.model.User;
import com.example.demo.model.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class UserController {
    @Autowired private UserService userService;

    @PostMapping("/login")
    public boolean login(@RequestBody User user) {
        return userService.isValidLogin(user);
    }

    //TODO: Vérifier qu'un authToken est présent dans la requête
    @GetMapping("/user/{username}")
    public Profil user(HttpServletRequest request, @PathVariable String username) {
        User user = userService.getUserLoginFromRequest(request);
        Profil profil = new Profil(username);

        // Si l'utilisateur connecté veut regarder son propre profil
        if (username.equals(user.getUsername()) && userService.isValidLogin(user)) {
            // On obtiendra plus d'informations en regardant son propre profil
            return profil;
        } 

        return profil;
    }

    /**
     * Renvoie l'id de l'utilisateur connecté
     */
    @GetMapping("/user/id")
    public Long userId(HttpServletRequest request) {
        return userService.getUserIdFromRequest(request);
    }
}
