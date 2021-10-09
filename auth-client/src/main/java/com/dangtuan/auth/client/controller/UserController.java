package com.dangtuan.auth.client.controller;

import com.dangtuan.auth.client.exception.ResourceNotFoundException;
import com.dangtuan.auth.client.model.User;
import com.dangtuan.auth.client.repository.UserRepository;
import com.dangtuan.auth.client.security.CurrentUser;
import com.dangtuan.auth.client.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user/me")
    @PreAuthorize("hasRole('USER')")
    public User getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        return userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
    }
}
