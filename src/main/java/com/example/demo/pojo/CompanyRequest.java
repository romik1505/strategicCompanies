package com.example.demo.pojo;

import lombok.Data;

import java.math.BigInteger;

@Data
public class CompanyRequest {
    private String name;
    private BigInteger ogrn;
    private BigInteger inn;
    private BigInteger kpp;
    private String address;
    private String director;
    private String egrn;
}
