package com.Sharif.votingapp.controller;

import com.Sharif.votingapp.model.Candidate;
import com.Sharif.votingapp.model.Election;
import com.Sharif.votingapp.service.CandidateService;
import com.Sharif.votingapp.service.ElectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/candidates")
public class CandidateController {

    @Autowired
    private CandidateService candidateService;

    @Autowired
    private ElectionService electionService;

    @GetMapping
    public String listCandidates(Model model) {
        List<Candidate> candidates = candidateService.getAllCandidates();
        model.addAttribute("candidates", candidates);
        return "admin/candidates";
    }

    @GetMapping("/new")
    public String showNewCandidateForm(Model model) {
        model.addAttribute("candidate", new Candidate());
        model.addAttribute("elections", electionService.getAllElections());
        return "admin/new-candidate";
    }

    @PostMapping
    public String saveCandidate(@ModelAttribute Candidate candidate, @RequestParam Long electionId) {
        Election election = electionService.getAllElections().stream()
                .filter(e -> e.getId().equals(electionId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Election not found"));

        candidate.setElection(election);
        candidateService.saveCandidate(candidate);
        return "redirect:/admin/candidates";
    }

    @PostMapping("/delete")
    public String deleteCandidate(@RequestParam Long candidateId) {
        candidateService.deleteCandidate(candidateId);
        return "redirect:/admin/candidates";
    }

    // Show candidate registration form
    @GetMapping("/candidates/register")
    public String showCandidateRegistrationForm(Model model) {
        model.addAttribute("candidate", new Candidate());
        model.addAttribute("elections", electionService.findAllElections());
        return "register-candidate";
    }

    // Handle candidate registration form submission
    @PostMapping("/candidates/register")
    public String registerCandidate(@ModelAttribute Candidate candidate) {
        candidateService.saveCandidate(candidate);
        return "redirect:/candidates"; // Redirect to candidate list page or another appropriate page
    }
}
