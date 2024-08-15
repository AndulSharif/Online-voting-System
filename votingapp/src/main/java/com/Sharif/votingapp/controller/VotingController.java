package com.Sharif.votingapp.controller;

import com.Sharif.votingapp.model.Candidate;
import com.Sharif.votingapp.model.User;
import com.Sharif.votingapp.service.CandidateService;
import com.Sharif.votingapp.service.UserService;
import com.Sharif.votingapp.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;
import java.util.Optional;

@Controller
public class VotingController {

    @Autowired
    private CandidateService candidateService;

    @Autowired
    private VoteService voteService;

    @Autowired
    private UserService userService;

    // List all candidates
    @GetMapping("/candidates")
    public String listCandidates(Model model) {
        model.addAttribute("candidates", candidateService.getAllCandidates());
        return "candidates";
    }

    // Cast a vote
    @PostMapping("/vote")
    public String castVote(@RequestParam Long candidateId, @AuthenticationPrincipal UserDetails loggedUser) {
        String username = loggedUser.getUsername();
        voteService.castVote(username, candidateId);
        return "redirect:/candidates";
    }

    // View voting results
    @GetMapping("/results")
    public String viewResults(Model model) {
        Map<Candidate, Long> results = voteService.getVotingResults();
        model.addAttribute("results", results);
        return "results";
    }

    // View user profile
    @GetMapping("/profile")
    public String viewUserProfile(@AuthenticationPrincipal UserDetails loggedUser, Model model) {
        String username = loggedUser.getUsername();
        Optional<User> userOptional = userService.findByUsername(username);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            model.addAttribute("user", user);
            return "profile";
        } else {
            throw new RuntimeException("User not found with username: " + username);
        }
    }
}
