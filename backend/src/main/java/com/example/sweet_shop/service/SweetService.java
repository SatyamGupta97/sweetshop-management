package com.example.sweet_shop.service;

import com.example.sweet_shop.model.Sweet;
import com.example.sweet_shop.repository.SweetRepository;
import org.springframework.stereotype.Service;

@Service
public class SweetService {

    private final SweetRepository sweetRepository;

    public SweetService(SweetRepository sweetRepository) {
        this.sweetRepository = sweetRepository;
    }

    public Sweet addSweet(Sweet sweet) {
        // For now, we just save the sweet directly.
        // We can add validation logic later.
        return sweetRepository.save(sweet);
    }
}