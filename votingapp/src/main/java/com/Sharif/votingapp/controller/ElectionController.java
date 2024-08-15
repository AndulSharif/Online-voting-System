package com.Sharif.votingapp.controller;

import com.Sharif.votingapp.model.Election;
import com.Sharif.votingapp.service.ElectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/elections")
public class ElectionController {

    @Autowired
    private ElectionService electionService;

    @GetMapping
    public String listElections(Model model) {
        List<Election> elections = electionService.getAllElections();
        model.addAttribute("elections", elections);
        return "admin/elections";
    }

    @GetMapping("/new")
    public String showNewElectionForm(Model model) {
        model.addAttribute("election", new Election());
        return "admin/new-election";
    }

    @PostMapping
    public String saveElection(@ModelAttribute Election election) {
        electionService.saveElection(election);
        return "redirect:/admin/elections";
    }

    @PostMapping("/delete")
    public String deleteElection(@RequestParam Long electionId) {
        electionService.deleteElection(electionId);
        return "redirect:/admin/elections";
    }
}
