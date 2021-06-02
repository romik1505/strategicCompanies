package com.example.demo.models;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title, text;
    @Temporal(TemporalType.DATE)
    private Date date;

    @ManyToOne
    Company company;
}
