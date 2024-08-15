package com.Sharif.votingapp.service;

import com.Sharif.votingapp.model.Election;
import com.Sharif.votingapp.repository.ElectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ElectionService {

    @Autowired
    private ElectionRepository electionRepository;

    public List<Election> getAllElections() {
        return electionRepository.findAll();
    }

    public void saveElection(Election election) {
        electionRepository.save(election);
    }

    public void deleteElection(Long electionId) {
        electionRepository.deleteById(electionId);
    }

    public Object findAllElections() {
        return electionRepository.findAll();
    }
}
