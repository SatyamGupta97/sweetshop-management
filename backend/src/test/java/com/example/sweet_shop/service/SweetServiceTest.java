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

    @Test
    void whenUpdateSweet_thenDetailsShouldBeUpdated() {
        // Arrange: Create and save an initial sweet
        Sweet originalSweet = new Sweet();
        originalSweet.setName("Old Name");
        originalSweet.setPrice(10.00);
        originalSweet.setQuantity(50);
        Sweet savedSweet = sweetRepository.save(originalSweet);
        Long sweetId = savedSweet.getId();

        // Create an object with the updated information
        Sweet updatedInfo = new Sweet();
        updatedInfo.setName("New Updated Name");
        updatedInfo.setPrice(12.50);
        updatedInfo.setQuantity(40);

        // Act: Call the update method that doesn't exist yet
        Sweet resultSweet = sweetService.updateSweet(sweetId, updatedInfo);

        // Assert
        assertNotNull(resultSweet);
        assertEquals(sweetId, resultSweet.getId());
        assertEquals("New Updated Name", resultSweet.getName());
        assertEquals(12.50, resultSweet.getPrice());
        assertEquals(40, resultSweet.getQuantity());
    }

    @Test
    void whenDeleteSweet_thenItShouldBeRemoved() {
        // Arrange
        Sweet sweet = new Sweet();
        sweet.setName("To Be Deleted");
        Sweet savedSweet = sweetRepository.save(sweet);
        Long sweetId = savedSweet.getId();

        // Act: Call the delete method which doesn't exist yet
        sweetService.deleteSweet(sweetId);

        // Assert
        assertFalse(sweetRepository.findById(sweetId).isPresent());
    }
}