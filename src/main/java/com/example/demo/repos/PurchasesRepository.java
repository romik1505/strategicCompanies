package com.example.demo.repos;

import com.example.demo.models.Company;
import com.example.demo.models.Purchases;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PurchasesRepository extends JpaRepository<Purchases, Long> {
    List<Purchases> findAll();
//    List<Purchases> findAllByCompany_Name(String companyname);
    List <Purchases> findAllByCompanyNameContains(String companyname);
    List <Purchases> findPurchasesByCompanyNameContains(String companyname);
    //List <Purchases> find
}
