package com.example.demo.repos;

import com.example.demo.models.Company;
import com.example.demo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    List<Company> findAll();
    List<Company> findCompanyByUsersId(Long id);
}
