package com.example.demo.models;

import com.example.demo.pojo.CompanyRequest;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.List;

@Entity
@Data
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @Column(unique=true)
    private BigInteger ogrn;
    @Column(unique=true)
    private BigInteger inn;
    @Column(unique=true)
    private BigInteger kpp;
    private String address;
    private String director;
    private String egrn;

    @ManyToMany(fetch = FetchType.LAZY)
    @JsonIgnore
    private List<User> users;

    @OneToMany
    List<Purchases> purchasesList;
    @OneToMany
    @JsonIgnore
    List<Post> posts;

    public Company(CompanyRequest companyRequest) {
        this.name = companyRequest.getName();
        this.ogrn = companyRequest.getOgrn();
        this.inn = companyRequest.getInn();
        this.kpp = companyRequest.getKpp();
        this.address = companyRequest.getAddress();
        this.director = companyRequest.getDirector();
        this.egrn = companyRequest.getEgrn();
    }

    public Company() {

    }
}
