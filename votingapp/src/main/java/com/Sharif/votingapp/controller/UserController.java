package com.Sharif.votingapp.controller;

import com.Sharif.votingapp.model.User;
import com.Sharif.votingapp.service.UserService;
import com.Sharif.votingapp.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private VoteService voteService;

    // Registration
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user) {
        userService.saveUser(user);
        return "redirect:/login";
    }

    // Login
    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    // Logout
    @GetMapping("/logout-success")
    public String logout() {
        return "logout";
    }

    // Profile
    @GetMapping("/viewprofile")
    public String viewProfile(@AuthenticationPrincipal UserDetails loggedUser, Model model) {
        String username = loggedUser.getUsername();
        User user = userService.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found with username: " + username));

        model.addAttribute("user", user);
        return "profile";
    }
}
