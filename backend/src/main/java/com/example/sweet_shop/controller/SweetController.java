package com.example.sweet_shop.controller;

import com.example.sweet_shop.dto.SweetRequestDto;
import com.example.sweet_shop.model.Sweet;
import com.example.sweet_shop.service.SweetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sweets")
public class SweetController {

    private final SweetService sweetService;

    public SweetController(SweetService sweetService) {
        this.sweetService = sweetService;
    }

    @PostMapping
    public ResponseEntity<Sweet> createSweet(@RequestBody SweetRequestDto sweetRequestDto) {
        Sweet sweetToCreate = new Sweet();
        sweetToCreate.setName(sweetRequestDto.getName());
        sweetToCreate.setCategory(sweetRequestDto.getCategory());
        sweetToCreate.setPrice(sweetRequestDto.getPrice());
        sweetToCreate.setQuantity(sweetRequestDto.getQuantity());

        Sweet createdSweet = sweetService.addSweet(sweetToCreate);
        return new ResponseEntity<>(createdSweet, HttpStatus.CREATED);
    }
}