package com.example.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class PurchasesRequest {
    String num;
    String status;
    String price;
    Date date;
    String subject;
}
