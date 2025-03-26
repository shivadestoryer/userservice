package com.users.userservice.dao;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "auth_logs")
@Data
public class AuthLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false, length = 45)
    private String ipAddress;

    @Column(nullable = false)
    private LocalDateTime loginTime = LocalDateTime.now();

    private LocalDateTime logoutTime;

    // Constructors, Getters, Setters, toString() omitted for brevity
}

