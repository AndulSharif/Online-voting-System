package com.Sharif.votingapp.service;

import com.Sharif.votingapp.model.Candidate;
import com.Sharif.votingapp.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CandidateService {

    @Autowired
    private CandidateRepository candidateRepository;

    // Save a new candidate
    public void saveCandidate(Candidate candidate) {
        candidateRepository.save(candidate);
    }

    // Delete a candidate by ID
    public void deleteCandidate(Long id) {
        candidateRepository.deleteById(id);
    }

    // Get all candidates
    public List<Candidate> findAllCandidates() {
        return candidateRepository.findAll();
    }

    // Additional method for getting all candidates
    public List<Candidate> getAllCandidates() {
        return findAllCandidates(); // Reusing findAllCandidates method
    }
}
