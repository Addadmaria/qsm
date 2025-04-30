package com.universite.qsm.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.universite.qsm.dtos.UserDTO;
import com.universite.qsm.dtos.UseradminDto;
import com.universite.qsm.entities.User;
import com.universite.qsm.repositories.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody UserDTO dto) {
        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPasswordHash(dto.getPassword()); // à sécurise plus tard
        user.setRole(User.Role.valueOf(dto.getRole()));
        userRepository.save(user);
        return ResponseEntity.ok("Utilisateur créé avec succès !");
    }

    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/users")
    public List<UseradminDto> getUsers() {
        return userRepository.findAll().stream()
        .map(user -> new UseradminDto(
            user.getUserId(),
            user.getName(),
            user.getEmail(),
            user.getRole().name()
        )).collect(Collectors.toList()); // Added closing parenthesis and collect() method
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDTO dto) {
        Optional<User> userOpt = userRepository.findByEmail(dto.getEmail());

        if (userOpt.isEmpty() || !userOpt.get().getPasswordHash().equals(dto.getPassword())) {
            return ResponseEntity.status(401).body("Email ou mot de passe incorrect.");
        }

        User user = userOpt.get();

        // Construction d'une réponse minimale pour le front
        UserDTO responseDTO = new UserDTO();
        responseDTO.setUserId(user.getUserId());
        responseDTO.setName(user.getName());
        responseDTO.setEmail(user.getEmail());
        responseDTO.setRole(user.getRole().name());

        return ResponseEntity.ok().body(new java.util.HashMap<>() {{
            put("success", true);
            put("user", responseDTO);
        }});
    }
}