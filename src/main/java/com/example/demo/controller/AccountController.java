package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/account")
@CrossOrigin(origins = "*")
public class AccountController {
    @GetMapping("/my-account")
    public  ResponseEntity<String> getAccountDetails(){

        return ResponseEntity.ok("here are the updated account details");
    }
}
