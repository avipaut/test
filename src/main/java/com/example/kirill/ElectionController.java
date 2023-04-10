package com.example.kirill;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ElectionController {

    private final ElectionService electionService;

    public ElectionController(ElectionService electionService) {
        this.electionService = electionService;
    }

    @PostMapping("/register")
    public Voter register(@RequestBody RegisterRequest registerRequest) {
        return electionService.registerVoter(registerRequest.getUsername(), registerRequest.getPassword());
    }

    @PostMapping("/vote")
    public ResponseEntity<String> vote(@RequestBody VoteRequest voteRequest) {
        boolean success = electionService.vote(voteRequest.getUsername(), voteRequest.getCandidateName());
        if (success) {
            return ResponseEntity.ok("Vote recorded successfully.");
        } else {
            return ResponseEntity.badRequest().body("Could not record vote. Please check your username and try again.");
        }
    }

    @GetMapping("/candidates")
    public List<Candidate> getCandidates() {
        return electionService.getCandidates();
    }

    @GetMapping("/winner")
    public Candidate getWinner() {
        return electionService.getWinner();
    }
}

