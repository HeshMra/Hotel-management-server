package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepo;
import com.example.demo.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@CrossOrigin
public class LoginController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepo userRepo;


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<String>regsiterUser(@RequestBody User user){
        ResponseEntity response = null;
        try{
            String hashPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(hashPassword);
            User savedUser = userRepo.save(user);
            if(savedUser.getId()>0){
                response=ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body("Given User Details Are Successfully registered");
            }


        }catch (Exception ex){
            response=ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body("An Exception Occured due to"+ ex.getMessage());
        }
        return response;
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody User user) {
        try {
            // Authenticate the user
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

            // Retrieve the user from the database
            User foundUser = userRepo.findByUsername(user.getUsername());
            if (foundUser == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
            }

            // Extract roles and generate token
            List<String> roles = Collections.singletonList(foundUser.getRole()); // Assuming getRole() returns the role as a String
            String token = jwtUtil.generateToken(foundUser.getUsername(), roles);

            return ResponseEntity.ok(token);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid credentials");
        }
    }



}
