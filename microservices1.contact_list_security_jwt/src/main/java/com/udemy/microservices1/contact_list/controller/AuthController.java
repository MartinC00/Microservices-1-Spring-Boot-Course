package com.udemy.microservices1.contact_list.controller;

import com.udemy.microservices1.contact_list.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.stream.Collectors;

import static com.udemy.microservices1.contact_list.util.Constants.TOKEN_KEY;
import static com.udemy.microservices1.contact_list.util.Constants.TOKEN_LIFE_DURATION;

@RestController
public class AuthController {
    private AuthenticationManager authManager;

    public AuthController(AuthenticationManager authManager) {
        super();
        this.authManager = authManager;
    }

    @PostMapping(value = "login")
    public ResponseEntity<String> login(@RequestBody User user){
        try {
            Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
            return ResponseEntity.ok(getToken(authentication));
        }catch (AuthenticationException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Failed authentication. Please check credentials and try again.");
        }
    }
    private String getToken(Authentication auth){
        return Jwts.builder()
                .setIssuedAt(new Date())
                .setSubject(auth.getName())
                .claim("authorities", auth.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList()))
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_LIFE_DURATION))
                .signWith(Keys.hmacShaKeyFor(TOKEN_KEY))
                .compact();
    }

}
