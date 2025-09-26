package com.example.sweet_shop.controller;

import com.example.sweet_shop.dto.SweetRequestDto;
import com.example.sweet_shop.model.Sweet;
import com.example.sweet_shop.service.SweetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*; // Update this import
import java.util.List;

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

    @GetMapping
    public ResponseEntity<List<Sweet>> getAllSweets() {
        List<Sweet> sweets = sweetService.getAllSweets();
        return ResponseEntity.ok(sweets);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Sweet>> searchSweets(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice) {
        List<Sweet> sweets = sweetService.searchSweets(name, category, minPrice, maxPrice);
        return ResponseEntity.ok(sweets);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Sweet> updateSweet(@PathVariable Long id, @RequestBody SweetRequestDto sweetRequestDto) {
        Sweet updatedInfo = new Sweet();
        updatedInfo.setName(sweetRequestDto.getName());
        updatedInfo.setCategory(sweetRequestDto.getCategory());
        updatedInfo.setPrice(sweetRequestDto.getPrice());
        updatedInfo.setQuantity(sweetRequestDto.getQuantity());

        Sweet updatedSweet = sweetService.updateSweet(id, updatedInfo);
        return ResponseEntity.ok(updatedSweet);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')") // This secures the endpoint
    public ResponseEntity<String> deleteSweet(@PathVariable Long id) {
        sweetService.deleteSweet(id);
        return ResponseEntity.ok("Sweet deleted successfully.");
    }
}