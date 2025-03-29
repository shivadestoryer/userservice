package com.users.userservice.dao;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "user_profiles")
@Data
public class UserProfile {

    @Id
    @Column(name = "user_id")
    private int userId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String address;
    private String profilePicture;
    private LocalDate dateOfBirth;

    // Constructors, Getters, Setters, toString() omitted for brevity
}

