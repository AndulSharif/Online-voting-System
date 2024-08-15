package com.Sharif.votingapp.service;

import com.Sharif.votingapp.model.Candidate;
import com.Sharif.votingapp.model.User;
import com.Sharif.votingapp.repository.CandidateRepository;
import com.Sharif.votingapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class VoteService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CandidateRepository candidateRepository;

    public void castVote(String username, Long candidateId) {
        User user = userRepository.findByUsername(username);
        Candidate candidate = candidateRepository.findById(candidateId).orElseThrow();
        user.getVotedCandidates().add(candidate);
        userRepository.save(user);
    }

    public Map<Candidate, Long> getVotingResults() {
        List<Candidate> candidates = candidateRepository.findAll();
        return candidates.stream()
                .collect(Collectors.toMap(
                        candidate -> candidate,
                        candidate -> (long) candidate.getVoters().size()
                ));
    }
}
