package com.example.demo.Controller;

import javax.servlet.http.HttpServletRequest;

import com.example.demo.model.GameRepository;
import com.example.demo.model.Profile;
import com.example.demo.model.User;
import com.example.demo.model.UserRepository;
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
    @Autowired private UserRepository userRepository;
    @Autowired private GameRepository gameRepository;

    @PostMapping("/login")
    public boolean login(@RequestBody User user) {
        return userService.isValidLogin(user);
    }

    @GetMapping("/user/{username}")
    public Profile user(HttpServletRequest request, @PathVariable String username) {
        User askingUser = userService.getUserLoginFromRequest(request);

        // Si l'utilisateur connecté veut regarder son propre profil
        if (username.equals(askingUser.getUsername()) && userService.isValidLogin(askingUser)) {
            // On obtiendra plus d'informations en regardant son propre profil
            return new Profile("Self");
        } 

        User askedUser = userRepository.findByUsername(username);
        if (askedUser == null) {
            //TODO: Renvoyer une erreur
            return null;
        }

        int totalGame = gameRepository.countTotalGame(username);
        int winCount = gameRepository.countUserWin(username);
        int loseCount = gameRepository.countUserLose(username);
        return new Profile(username, totalGame, winCount, loseCount);
    }

    /**
     * Renvoie l'id de l'utilisateur connecté
     */
    @GetMapping("/user/id")
    public Long userId(HttpServletRequest request) {
        return userService.getUserIdFromRequest(request);
    }
}
