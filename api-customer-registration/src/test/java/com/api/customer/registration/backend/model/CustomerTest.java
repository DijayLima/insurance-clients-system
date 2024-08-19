package com.api.customer.registration.backend.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CustomerTest {

    @Test
    @DisplayName("Test regarding the values generated before saving a new insurance contract.")
    void testPrePersist() {
        // Arrange
        Customer customer = new Customer();

        // Act
        customer.onCreate();

        // Assert
        assertNotNull(customer.getCreateAt());
        assertNotNull(customer.getUpdateAt());
    }

    @Test
    @DisplayName("Test for generated values before updating a new insurance contract.")
    void testPreUpdate() {
        // Arrange
        Customer customer = new Customer();

        // Act
        customer.onUpdate();

        // Assert
        assertNotNull(customer.getUpdateAt());
    }

}