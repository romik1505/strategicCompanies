package com.example.demo.controllers;

import com.example.demo.models.Company;
import com.example.demo.pojo.CompanyRequest;
import com.example.demo.pojo.MessageResponse;
import com.example.demo.repos.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/companies")
public class CompaniesController {
    @Autowired
    private CompanyRepository companyRepository;

    @GetMapping("/all")
    public List<Company> company(){
        return companyRepository.findAll();
    }

    @GetMapping
    public List<Company> searchCompany(@RequestParam String findBy, @RequestParam String value) {
        if (value.equals("") || value == null)
            return companyRepository.findAll();
        switch (findBy){
            case "name":
                return companyRepository.findCompaniesByNameContains(value);
            case "address":
                return companyRepository.findCompaniesByAddressContains(value);
            case "inn":
                return companyRepository.findCompaniesByInn(new BigInteger(value));
            case "kpp":
                return companyRepository.findCompaniesByKpp(new BigInteger(value));
            case "ogrn":
                return companyRepository.findCompaniesByOgrn(new BigInteger(value));
            case "director":
                return companyRepository.findCompaniesByDirectorContains(value);
        }
        return null;
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> addCompany(@RequestBody CompanyRequest companyRequest) {
        companyRepository.save(new Company(companyRequest));
        return ResponseEntity.ok(new MessageResponse("Company added"));
    }




    @GetMapping("/user/{userId}")
    public List<Company> getCompany (@RequestParam Long userId) {
        return companyRepository.findCompanyByUsersId(userId);
    }

}
