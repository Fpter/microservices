package com.example.Securityservice.controller;

import com.example.Securityservice.entity.User;
import com.example.Securityservice.util.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;

@RestController
public class AuthController {
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) throws NoSuchAlgorithmException {
        String token = JwtUtil.generateToken(user.getUsername());
        return ResponseEntity.ok(token);
    }

    @GetMapping("/api/private")
    public ResponseEntity<String> test() {

        return ResponseEntity.ok("success");
    }

}
