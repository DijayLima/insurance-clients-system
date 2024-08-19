package com.api.insurances.backend.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

public class InsuranceHiringTest {

    @Test
    @DisplayName("Test regarding the values generated before saving a new insurance contract.")
    void testPrePersist() {
        // Arrange
        InsuranceHiring insuranceHiring = new InsuranceHiring();
        insuranceHiring.setStatus("NEW");

        // Act
        insuranceHiring.onCreate();

        // Assert
        assertNotNull(insuranceHiring.getId());
        assertNotNull(insuranceHiring.getCreateAt());
        assertNotNull(insuranceHiring.getUpdateAt());
        assertNotNull(insuranceHiring.getDateHiring());
        assertEquals("CURRENT", insuranceHiring.getStatus());
    }

    @Test
    @DisplayName("Test for generated values before updating a new insurance contract.")
    void testPreUpdate() {
        // Arrange
        InsuranceHiring insuranceHiring = new InsuranceHiring();

        // Act
        insuranceHiring.onUpdate();

        // Assert
        assertNotNull(insuranceHiring.getUpdateAt());
    }
}
