package com.example.demo.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class User implements UserDetails {
    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private String username;
    private String password;

    public User() {}
    public User(String _username, String _password) { this.username = _username; this.password = _password; }

	@Override public Collection<? extends GrantedAuthority> getAuthorities() { return null; }
	@Override public boolean isAccountNonExpired() { return true; }
	@Override public boolean isAccountNonLocked() { return true; }
	@Override public boolean isCredentialsNonExpired() { return true; }
	@Override public boolean isEnabled() { return true; }
}

