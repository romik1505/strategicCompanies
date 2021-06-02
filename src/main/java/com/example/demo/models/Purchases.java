package com.example.demo.models;

import com.example.demo.pojo.PurchasesRequest;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Purchases {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Temporal(TemporalType.DATE)
    private Date date;
    private Long price;
    private String contract_number;
    private String subject_contract;
    private String method_purchase;

    @ManyToOne
    @JsonBackReference
    private Company company;

}
