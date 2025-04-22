package com.universite.qsm.controllers;

import lombok.RequiredArgsConstructor; 
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.universite.qsm.dtos.UserDTO;
import com.universite.qsm.entities.User;
import com.universite.qsm.repositories.UserRepository;

import java.sql.Types;
import java.util.List;

@RestController @RequestMapping("/api/users") @RequiredArgsConstructor 
public class UserController {


@Autowired
private UserRepository userRepository;

@PostMapping("/create")
public ResponseEntity<?> createUser(@RequestBody UserDTO dto) {
    User user = new User();
    user.setName(dto.getName());
    user.setEmail(dto.getEmail());
    user.setPasswordHash("1234"); // à sécuriser plus tard
    user.setRole(User.Role.valueOf(dto.getRole()));

    userRepository.save(user);

    return ResponseEntity.ok("Utilisateur créé avec succès !");
}

@GetMapping("/all")
public List<User> getAllUsers() {
    return userRepository.findAll();
}
}