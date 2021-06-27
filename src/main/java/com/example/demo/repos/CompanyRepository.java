package com.example.demo.repos;

import com.example.demo.models.Company;
import com.example.demo.models.Purchases;
import com.example.demo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.math.BigInteger;
import java.util.List;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    List<Company> findAll();
    List<Company> findCompanyByUsersId(Long id);
    Company findCompanyById(Long id);
    List<Company> findCompaniesByNameContains(String name);
    List<Company> findCompaniesByAddressContains(String address);
    List<Company> findCompaniesByInn(BigInteger inn);
    Company findCompanyByInn(BigInteger inn);
    List<Company> findCompaniesByKpp(BigInteger kpp);
    List<Company> findCompaniesByOgrn(BigInteger ogrn);
    List<Company> findCompaniesByUsersId(long id);
    List<Company> findCompaniesByDirectorContains(String name);
}
