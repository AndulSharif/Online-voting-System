package com.Sharif.votingapp.service;

import com.Sharif.votingapp.model.User;
import com.Sharif.votingapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Save a new user
    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword())); // Encrypt password
        user.setRole("ROLE_VOTER"); // Set default role
        userRepository.save(user); // Save user to the database
    }


    // Find a user by username
    public Optional<User> findByUsername(String username) {
        return Optional.ofNullable(userRepository.findByUsername(username));
    }

    // Find all Users
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public void vote(String username, Long candidateId) {
        Optional<User> userOptional = Optional.ofNullable(userRepository.findByUsername(username));
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            // Proceed with the voting logic
        } else {
            throw new RuntimeException("User not found with username: " + username);
        }
    }


}
