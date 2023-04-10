package com.example.kirill;

import org.springframework.jdbc.core.JdbcTemplate;

public class DatabaseInitializer {
    private final JdbcTemplate jdbcTemplate;

    public DatabaseInitializer(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void initialize() {
        jdbcTemplate.execute("CREATE TABLE voters (id INTEGER PRIMARY KEY, username VARCHAR(255), password VARCHAR(255))");
        jdbcTemplate.execute("CREATE TABLE candidates (id INTEGER PRIMARY KEY, name VARCHAR(255))");
        jdbcTemplate.execute("CREATE TABLE votes (id INTEGER PRIMARY KEY, voter_id INTEGER, candidate_id INTEGER, FOREIGN KEY (voter_id) REFERENCES voters (id), FOREIGN KEY (candidate_id) REFERENCES candidates (id))");
    }
}
