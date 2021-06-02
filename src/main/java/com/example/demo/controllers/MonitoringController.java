package com.example.demo.controllers;

import com.example.demo.models.Company;
import com.example.demo.models.User;
import com.example.demo.repos.CompanyRepository;
import com.example.demo.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/monitoring")

@CrossOrigin(origins = "*", maxAge = 3600)
public class MonitoringController {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public void userAccess(@RequestParam String userId) {
//        User user = userRepository.findById(Long.parseLong(userId)).get();
//        return user.getCompanies();
//        return companyRepository.findCompaniesByIdUsers(Long.parseLong(userId));
    }

    @PostMapping("/user")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public void linkUserCompany(@RequestParam String userId, @RequestParam String companyId) {

//        User user = userRepository.findById(Long.parseLong(userId)).get();
//        return user.getCompanies();

        //userRepository.findById(Long.parseLong(userId)).get().

    }
}
