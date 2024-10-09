package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@CrossOrigin
public class LoginController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepo userRepo;

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
}
