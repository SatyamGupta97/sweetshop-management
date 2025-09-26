package com.example.sweet_shop.dto;

import lombok.Data;

@Data
public class SweetRequestDto {
    private String name;
    private String category;
    private double price;
    private int quantity;


}