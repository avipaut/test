package com.example.kirill;

import jakarta.persistence.*;
import org.springframework.data.annotation.Id;

@Entity
@Table(name = "voters")
public class Voter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private boolean hasVoted;

    // constructors, getters and setters omitted for brevity
}

