package com.api.insurances.backend.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "insurance_hiring")
public class InsuranceHiring {

    @Id
    private String id;

    @Column(nullable = false, name = "customer_id")
    private String customerId;

    @Column(nullable = false, name = "insurance")
    private int insurance;

    @Column(nullable = false, name = "value_monthly")
    private Double valueMonthly;

    @Column(nullable = false, name = "date_hiring")
    private LocalDateTime dateHiring;

    @Column(nullable = false, name = "status")
    private String status;

    @Column(nullable = false, name = "create_at")
    private LocalDateTime createAt;

    @Column(nullable = false, name = "update_at")
    private LocalDateTime updateAt;

    @PrePersist
    protected void onCreate() {
        id = UUID.randomUUID().toString();
        dateHiring = LocalDateTime.now();
        createAt = LocalDateTime.now();
        updateAt = LocalDateTime.now();
        status = "CURRENT";
    }

    @PreUpdate
    protected void onUpdate() {
        updateAt = LocalDateTime.now();
    }

}
