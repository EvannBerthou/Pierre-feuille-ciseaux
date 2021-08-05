package com.example.demo.model;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class MyUserDetailsService implements UserDetailsService {

    //TODO: A remplacer par la base de donnée.
    private final User[] users = new User[] {
        new User("admin", "admin"),
        new User("user",  "pass"),
    };

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = null;
        for (User u : users) {
            if (u.getUsername().equals(username)) {
                user = u;
                break;
            }
        }

        if (user == null) throw new UsernameNotFoundException("User not found");

        UserBuilder builder = 
            org.springframework.security.core.userdetails.User.withUsername(username);
        builder.password(new BCryptPasswordEncoder().encode(user.getPassword()));
        builder.roles("USER");

        return builder.build();
	}
}
