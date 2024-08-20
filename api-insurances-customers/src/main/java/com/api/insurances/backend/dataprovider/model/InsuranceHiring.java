package com.api.insurances.backend.dataprovider.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "insurance_hiring")
@EntityListeners(AuditingEntityListener.class)
public class InsuranceHiring {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(updatable = false, nullable = false)
    private String id;

    @Column(nullable = false, name = "customer_id")
    private String customerId;

    @Column(nullable = false, name = "insurance")
    private int insurance;

    @Column(nullable = false, name = "value_monthly")
    private Double valueMonthly;

    @CreatedDate
    @Column(nullable = false, name = "date_hiring")
    private LocalDateTime dateHiring;

    @CreatedDate
    @Column(nullable = false, name = "create_at")
    private LocalDateTime createAt;

    @LastModifiedDate
    @Column(nullable = false, name = "update_at")
    private LocalDateTime updateAt;

}
