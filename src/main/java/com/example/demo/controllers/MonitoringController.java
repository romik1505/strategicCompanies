package com.example.demo.controllers;

import com.example.demo.models.Company;
import com.example.demo.models.User;
import com.example.demo.pojo.Subscribe;
import com.example.demo.repos.CompanyRepository;
import com.example.demo.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/monitoring")

@CrossOrigin(origins = "*", maxAge = 3600)
public class MonitoringController {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private UserRepository userRepository;


    @GetMapping("/user")
//    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public List<Company> monitoring(@RequestParam Long userId) {
        return companyRepository.findCompaniesByUsersId(userId);
    }

    @PostMapping("/subscribe")
//    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public void linkUserCompany(@RequestBody Subscribe subscribe) {
        Company company = companyRepository.findCompanyById(subscribe.getCompanyId().longValue());
        User user = userRepository.findUserById(subscribe.getUserId().longValue());

        user.getCompanies().add(company);
        userRepository.save(user);
    }

    @PostMapping("/unsubscribe")
//    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public void unlinkUserCompany(@RequestParam Long userId, @RequestParam Long companyId) {
        Company company = companyRepository.findCompanyById(companyId);
        User user = userRepository.findUserById(userId);

        user.getCompanies().remove(company);
        userRepository.save(user);
    }
}
