package com.project.LibraryManagementSystem.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Getter
@Setter
public class User 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    private String Name;

    @Column(nullable = false)
    private String password; // Encrypted Password

    @Column(nullable = false)
    private String role; // ADMIN, LIBRARIAN, MEMBER
}