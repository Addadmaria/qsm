package com.universite.qsm.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "users")

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;  // MENTOR or STUDENT

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    // If mentor, their created exams:
    @OneToMany(mappedBy = "createdBy", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Exam> exams;

    // If student, their attempts:
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Attempt> attempts;

    // getters/setters, constructors...

    public enum Role {
        MENTOR, STUDENT
    }

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

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public List<Exam> getExams() {
		return exams;
	}

	public void setExams(List<Exam> exams) {
		this.exams = exams;
	}

	public List<Attempt> getAttempts() {
		return attempts;
	}

	public void setAttempts(List<Attempt> attempts) {
		this.attempts = attempts;
	}

	public User() {
		this.name = name;
		this.email = email;
		this.passwordHash = passwordHash;
		this.role = role;
		this.createdAt = createdAt;
		this.exams = exams;
		this.attempts = attempts;
	}

    // ... constructors, getters/setters
    
}
