package com.example.sweet_shop.service;

import com.example.sweet_shop.model.Sweet;
import com.example.sweet_shop.repository.SweetRepository;
import jakarta.persistence.EntityNotFoundException; // Add this import
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

    public Sweet updateSweet(Long id, Sweet updatedInfo) {
        Sweet existingSweet = sweetRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Sweet not found with id: " + id));

        existingSweet.setName(updatedInfo.getName());
        existingSweet.setPrice(updatedInfo.getPrice());
        existingSweet.setQuantity(updatedInfo.getQuantity());

        return sweetRepository.save(existingSweet);
    }

    public void deleteSweet(Long id) {
        if (!sweetRepository.existsById(id)) {
            throw new EntityNotFoundException("Sweet not found with id: " + id);
        }
        sweetRepository.deleteById(id);
    }
}