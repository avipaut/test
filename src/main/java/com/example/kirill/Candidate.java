package com.example.kirill;

import jakarta.persistence.*;
import org.springframework.data.annotation.Id;

@Entity
@Table(name = "candidates")
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private int voteCount;

    // constructors, getters and setters omitted for brevity
}
