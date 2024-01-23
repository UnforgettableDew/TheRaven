package com.unforgettable.theraven.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "customer")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "created")
    private Long created;

    @Column(name = "updated")
    private Long updated;

    @Column(name = "full_name", nullable = false, length = 50)
    private String fullName;

    @Column(name = "email", unique = true, nullable = false, length = 100)
    private String email;

    @Column(name = "phone", length = 14)
    private String phone;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive;
}
