package com.api.insurances.backend.cross.enumeration;

import com.api.insurances.backend.cross.exception.InsuranceException;
import lombok.Getter;

@Getter
public enum InsuranceType {
    BRONZE(1, "Seguro de Bronze", "Básico", 99.99),
    PRATA(2, "Seguro de Prata", "Intermediário", 199.99),
    OURO(3, "Seguro de Ouro", "Completo", 399.99);

    private final int id;
    private final String name;
    private final String description;
    private final double valueBase;

    InsuranceType(int id, String name, String description, double valueBase) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.valueBase = valueBase;
    }

    public static InsuranceType findById(int id) {
        for (InsuranceType type : values()) {
            if (type.id == id) {
                return type;
            }
        }
        throw new InsuranceException("Insurance not found with ID: " + id);
    }}

