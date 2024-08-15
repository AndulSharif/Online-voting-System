package com.Sharif.votingapp.repository;

import com.Sharif.votingapp.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<Vote, Long> {
}
