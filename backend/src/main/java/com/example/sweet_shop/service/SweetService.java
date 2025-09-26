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
        return sweetRepository.findAll((Specification<Sweet>) (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (name != null && !name.isBlank()) {
                predicates.add(cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%"));
            }
            if (category != null && !category.isBlank()) {
                predicates.add(cb.like(cb.lower(root.get("category")), "%" + category.toLowerCase() + "%"));
            }
            if (minPrice != null) {
                predicates.add(cb.ge(root.get("price"), minPrice));
            }
            if (maxPrice != null) {
                predicates.add(cb.le(root.get("price"), maxPrice));
            }
            return cb.and(predicates.toArray(new Predicate[0]));
        });
    }


    public Sweet updateSweet(Long id, Sweet updatedInfo) {
        Sweet existingSweet = sweetRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Sweet not found with id: " + id));

        existingSweet.setName(updatedInfo.getName());
        existingSweet.setPrice(updatedInfo.getPrice());
        existingSweet.setQuantity(updatedInfo.getQuantity());
        existingSweet.setCategory(updatedInfo.getCategory());


        return sweetRepository.save(existingSweet);
    }

    public void deleteSweet(Long id) {
        if (!sweetRepository.existsById(id)) {
            throw new EntityNotFoundException("Sweet not found with id: " + id);
        }
        sweetRepository.deleteById(id);
    }

    public Sweet purchaseSweet(Long id) {
        Sweet sweet = sweetRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Sweet not found with id: " + id));
        if (sweet.getQuantity() <= 0) {
            throw new RuntimeException("Sweet is out of stock");
        }
        sweet.setQuantity(sweet.getQuantity() - 1);
        return sweetRepository.save(sweet);
    }

    public Sweet restockSweet(Long id, int quantity) {
        Sweet sweet = sweetRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Sweet not found with id: " + id));
        sweet.setQuantity(sweet.getQuantity() + quantity);
        return sweetRepository.save(sweet);
    }



}