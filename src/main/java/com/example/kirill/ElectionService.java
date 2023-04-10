package com.example.kirill;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ElectionService {

    private final VoterRepository voterRepository;

    private final CandidateRepository candidateRepository;

    public ElectionService(VoterRepository voterRepository, CandidateRepository candidateRepository) {
        this.voterRepository = voterRepository;
        this.candidateRepository = candidateRepository;
    }

    public Voter registerVoter(String username, String password) {
        Voter voter = new Voter();
        voter.setUsername(username);
        voter.setPassword(password);
        return voterRepository.save(voter);
    }

    public boolean vote(String username, String candidateName) {
        Optional<Voter> optionalVoter = voterRepository.findByUsername(username);
        Optional<Candidate> optionalCandidate = candidateRepository.findByName(candidateName);

        if (optionalVoter.isPresent() && optionalCandidate.isPresent()) {
            Voter voter = optionalVoter.get();
            Candidate candidate = optionalCandidate.get();

            if (!voter.isHasVoted()) {
                candidate.setVoteCount(candidate.getVoteCount() + 1);
                candidateRepository.save(candidate);

                voter.setHasVoted(true);
                voterRepository.save(voter);

                return true;
            }
        }
        return false;
    }

    public List<Candidate> getCandidates() {
        return candidateRepository.findAll();
    }

    public Candidate getWinner() {
        return candidateRepository.findTopByOrderByVoteCountDesc();
    }
}
