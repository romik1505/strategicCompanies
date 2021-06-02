package com.example.demo.controllers;

import com.example.demo.Parser;
import com.example.demo.models.Company;
import com.example.demo.models.User;
import com.example.demo.pojo.CompanyRequest;
import com.example.demo.pojo.MessageResponse;
import com.example.demo.pojo.PurchasesRequest;
import com.example.demo.repos.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/companies")
public class CompaniesController {
    @Autowired
    private CompanyRepository companyRepository;

    @GetMapping
    public List<Company> company(){
        return companyRepository.findAll();
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> addCompany(@RequestBody CompanyRequest companyRequest) {
        companyRepository.save(new Company(companyRequest));
        return ResponseEntity.ok(new MessageResponse("Company added"));
    }

    @GetMapping("/user")
    public List<Company> getCompany (@RequestParam Long userId) {
        return companyRepository.findCompanyByUsersId(userId);
    }

}
