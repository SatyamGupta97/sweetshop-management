package com.example.sweet_shop.service;

import com.example.sweet_shop.model.Sweet;
import com.example.sweet_shop.repository.SweetRepository;
import org.springframework.stereotype.Service;
import java.util.List; // Add this import

@Service
public class SweetService {

    private final SweetRepository sweetRepository;

    public SweetService(SweetRepository sweetRepository) {
        this.sweetRepository = sweetRepository;
    }

    public Sweet addSweet(Sweet sweet) {
        return sweetRepository.save(sweet);
    }

    // This is the new method
    public List<Sweet> getAllSweets() {
        return sweetRepository.findAll();
    }
}