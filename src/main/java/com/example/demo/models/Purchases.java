package com.example.demo.models;

import com.example.demo.pojo.PurchasesRequest;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Purchases {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String number;
    private String status;
    private String price;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    private String iz;
    private String lot;
    private String sub;

    @ManyToOne
//    @JoinColumn(name = "company_id")
    private Company company;

    public Purchases(String number, String status, String price, Date date, String iz, String lot, String sub) {
        this.number = number;
        this.status = status;
        this.price = price;
        this.date = date;
        this.iz = iz;
        this.lot = lot;
        this.sub = sub;
    }

    public Purchases() {

    }
}
