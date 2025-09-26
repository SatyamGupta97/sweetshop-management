package com.example.sweet_shop.service;

import com.example.sweet_shop.model.Sweet;
import com.example.sweet_shop.repository.SweetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SweetServiceTest {

    @Autowired
    private SweetService sweetService;

    @Autowired
    private SweetRepository sweetRepository;
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

    @Test
    void whenSearchSweetsByName_thenReturnMatchingSweets() {
        // Arrange
        Sweet sweet1 = new Sweet();
        sweet1.setName("Milk Chocolate");
        sweet1.setCategory("Chocolate");
        sweetRepository.save(sweet1);

        Sweet sweet2 = new Sweet();
        sweet2.setName("Dark Chocolate");
        sweet2.setCategory("Chocolate");
        sweetRepository.save(sweet2);

        Sweet sweet3 = new Sweet();
        sweet3.setName("Caramel Swirl");
        sweet3.setCategory("Caramel");
        sweetRepository.save(sweet3);

        // Act: Call the search method which does not exist yet
        List<Sweet> results = sweetService.searchSweets("Chocolate", null, null, null);

        // Assert
        assertNotNull(results);
        assertEquals(2, results.size());
        assertTrue(results.stream().allMatch(s -> s.getName().contains("Chocolate")));
    }
}