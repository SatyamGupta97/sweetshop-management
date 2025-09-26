package com.example.sweet_shop.repository;

import com.example.sweet_shop.model.Sweet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SweetRepository extends JpaRepository<Sweet, Long>, JpaSpecificationExecutor<Sweet> {
}