package com.example.sweet_shop.service;

import com.example.sweet_shop.model.Sweet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SweetServiceTest {

    @Autowired
    private SweetService sweetService; // This will cause an error

    @Test
    void whenAddSweet_thenSweetShouldBeSaved() {
        // Arrange
        Sweet sweetToAdd = new Sweet(); // This will cause an error
        sweetToAdd.setName("Royal Chocolate");
        sweetToAdd.setCategory("Chocolate");
        sweetToAdd.setPrice(10.50);
        sweetToAdd.setQuantity(50);

        // Act
        Sweet savedSweet = sweetService.addSweet(sweetToAdd); // This will cause an error

        // Assert
        assertNotNull(savedSweet);
        assertNotNull(savedSweet.getId());
        assertEquals("Royal Chocolate", savedSweet.getName());
    }

    @Test
    void whenGetAllSweets_thenReturnListOfSweets() {
        // Arrange: Save some sweets to the database first
        Sweet sweet1 = new Sweet();
        sweet1.setName("Caramel Delight");
        sweetRepository.save(sweet1);

        Sweet sweet2 = new Sweet();
        sweet2.setName("Mint Swirl");
        sweetRepository.save(sweet2);

        // Act: Call the method that doesn't exist yet
        List<Sweet> sweets = sweetService.getAllSweets();

        // Assert
        assertNotNull(sweets);
        assertEquals(2, sweets.size());
    }
}