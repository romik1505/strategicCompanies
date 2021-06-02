package com.example.demo.controllers;

import com.example.demo.Parser;
import com.example.demo.models.Purchases;
import com.example.demo.pojo.PurchasesRequest;
import com.example.demo.repos.PurchasesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/purchases")
public class PurchasesController {
    @Autowired
    private PurchasesRepository purchasesRepository;

//    @GetMapping
//    public List<Purchases> showPurchase(){
//        return purchasesRepository.findAll();
//    }

    @GetMapping
    public List<Purchases> showPurchase(@RequestParam String companyName){
        return purchasesRepository.findPurchasesByCompanyNameContains(companyName);
    }

    @GetMapping("/all")
    public List<Purchases> showAllPurchase() {
        return purchasesRepository.findAll();
    }

//    @PostMapping("/add")
//    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
//    public List<PurchasesRequest> addPurchase(@RequestParam String inn, @RequestParam PurchasesRequest purchasesRequest) {
////        Purchases purchases = new Purchases()
////        purchasesRepository.save()
//        return null;
//    }

    @GetMapping("/parser")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public List<PurchasesRequest> parsePurchase(@RequestParam String inn, @RequestParam Integer count) {
        try {
            return new Parser().parsePurchase(inn, count);
        } catch (Exception e) {
        }
        return null;
    }




}
