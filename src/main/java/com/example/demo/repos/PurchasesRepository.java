package com.example.demo.repos;

import com.example.demo.models.Company;
import com.example.demo.models.Purchases;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

public interface PurchasesRepository extends JpaRepository<Purchases, Long> {
    List<Purchases> findAll();
    List<Purchases> findAllByCompanyName(String companyname);
    List<Purchases> findPurchasesByCompany_Id(Long id);
    List<Purchases> findPurchasesByCompany_Kpp(BigInteger kpp);
    List<Purchases> findPurchasesByCompany_Inn(BigInteger inn);
    List<Purchases> findPurchasesByCompany_Ogrn(BigInteger orgn);
    List<Purchases> findPurchasesByCompanyNameContains(String companyname);
    List<Purchases> findPurchasesByCompanyAddressContains(String address);
    List<Purchases> findPurchasesByDateAfterAndDateBefore(Date date1, Date date2);
    List<Purchases> findPurchasesByStatus(String status);
    List<Purchases> findPurchasesByLotContains(String lot);
    List<Purchases> findPurchasesByIzContains(String iz);
    List<Purchases> findPurchasesBySubContains(String sub);
}
