package com.example.sweet_shop.repository;

import com.example.sweet_shop.model.Sweet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SweetRepository extends JpaRepository<Sweet, Long> {
}