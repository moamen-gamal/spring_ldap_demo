package com.example.ldap_demo.controllers;

import com.example.ldap_demo.models.LdapUser;
import com.example.ldap_demo.services.LdapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private LdapService ldapService;

    @GetMapping("/addUser")
    public String user(Model model) {
        model.addAttribute("ldapUser", new LdapUser());
        return "addUserForm";
    }

    @PostMapping("/addUser")
    public String addUser(LdapUser ldapUser) {
        ldapService.addUser(ldapUser);
        return "addUserForm";
    }

    @GetMapping("/getUsers")
    public List<LdapUser> getUsers() {
        var users = ldapService.getAllUsers();
        return users;
    }
}
