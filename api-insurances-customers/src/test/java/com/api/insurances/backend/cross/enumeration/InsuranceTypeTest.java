package com.api.insurances.backend.cross.enumeration;

import com.api.insurances.backend.cross.exception.InsuranceException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InsuranceTypeTest {

    @Test
    @DisplayName("Retrieve InsuranceType by valid ID.")
    public void test_FindByValidId() {
        InsuranceType type = InsuranceType.findById(1);
        assertNotNull(type);
        assertEquals(InsuranceType.BRONZE, type);
    }

    @Test
    @DisplayName("Attempt to retrieve InsuranceType with invalid ID.")
    public void test_FindByInvalidId() {
        Exception exception = assertThrows(InsuranceException.class, () -> {
            InsuranceType.findById(999);
        });
        String expectedMessage = "Insurance not found with ID: 999";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

}