package com.api.customer.registration.backend.dataprovider.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "customer")
@EntityListeners(AuditingEntityListener.class)
public class Customer {

    @Id
    @Column(updatable = false, nullable = false)
    private String cpf;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private LocalDate dateOfBirth;

    @Column(nullable = true)
    private String phone;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    @CreatedDate
    @Column(nullable = true, name = "create_at")
    private LocalDateTime createAt;

    @LastModifiedDate
    @Column(nullable = true, name = "update_at")
    private LocalDateTime updateAt;

}
