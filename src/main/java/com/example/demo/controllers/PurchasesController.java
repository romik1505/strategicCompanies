package com.example.demo.controllers;

import com.example.demo.Parser.Parser;
import com.example.demo.models.Company;
import com.example.demo.models.Purchases;
import com.example.demo.pojo.CompanyRequest;
import com.example.demo.pojo.MessageResponse;
import com.example.demo.repos.CompanyRepository;
import com.example.demo.repos.PurchasesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/purchases")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PurchasesController {
    @Autowired
    private PurchasesRepository purchasesRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @GetMapping
    public List<Purchases> showPurchase(@RequestParam String findBy, @RequestParam String value){
        if(value.equals("") || value == null)
            return purchasesRepository.findAll();
        switch (findBy) {
            case "status":
                return purchasesRepository.findPurchasesByStatus(value);
            case "lot":
                return purchasesRepository.findPurchasesByLotContains(value);
            case "iz":
                return purchasesRepository.findPurchasesByIzContains(value);
            case "sub":
                return purchasesRepository.findPurchasesBySubContains(value);
        }
        return null;
    }

    @GetMapping("/date")
    public List<Purchases> getbyDate(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date date1,
                                     @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date date2){
        return purchasesRepository.findPurchasesByDateAfterAndDateBefore(date1, date2);
    }

    @GetMapping("/all")
    public List<Purchases> showAllPurchase() {
        return purchasesRepository.findAll();
    }


    @GetMapping("/parser")
//    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public List<Purchases> parsePurchase(@RequestParam String inn, @RequestParam Integer count) {
        try {
            return new Parser().parsePurchase(inn, count);
        } catch (Exception e) {
        }
        return null;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addPurchase(@RequestParam BigInteger inn, @RequestBody Purchases purchases){
        Company company = companyRepository.findCompanyByInn(inn);
        purchases.setCompany(company);
        purchasesRepository.save(purchases);
        return ResponseEntity.ok(new MessageResponse("Purchase added"));
    }



}
