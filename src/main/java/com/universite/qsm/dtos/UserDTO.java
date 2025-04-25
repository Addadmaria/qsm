package com.universite.qsm.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class UserDTO {
    private Long userId;
    private String name;
    private String email;
    private String role;
    private String password; // 🔥 Ajouté pour login

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() { // 🔥 Ajouté
        return password;
    }

    public void setPassword(String password) { // 🔥 Ajouté
        this.password = password;
    }

    public UserDTO() {
        this.name = name;
        this.email = email;
        this.role = role;
    }
}