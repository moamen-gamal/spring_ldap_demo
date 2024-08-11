package com.example.ldap_demo.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "Hello World";
    }

    @GetMapping("/details")
    public String details(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        // get authenticated user data from context
        String username = userDetails.getUsername();
        boolean accountNonExpired = userDetails.isAccountNonExpired();

        return username + " " + accountNonExpired;
    }
}
