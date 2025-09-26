package com.example.sweet_shop.service;

import com.example.sweet_shop.model.Sweet;
import com.example.sweet_shop.repository.SweetRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Service
public class SweetService {

    private final SweetRepository sweetRepository;

    public SweetService(SweetRepository sweetRepository) {
        this.sweetRepository = sweetRepository;
    }

    public Sweet addSweet(Sweet sweet) {
        return sweetRepository.save(sweet);
    }

    public List<Sweet> getAllSweets() {
        return sweetRepository.findAll();
    }

    public List<Sweet> searchSweets(String name, String category, Double minPrice, Double maxPrice) {
        return sweetRepository.findAll((Specification<Sweet>) (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (name != null && !name.isBlank()) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + name.toLowerCase() + "%"));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });
    }
}