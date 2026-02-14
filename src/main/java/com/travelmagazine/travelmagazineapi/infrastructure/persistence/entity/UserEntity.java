package com.travelmagazine.travelmagazineapi.infrastructure.persistence.entity;

import com.travelmagazine.travelmagazineapi.core.domain.model.enums.SubscriptionTier;
import com.travelmagazine.travelmagazineapi.core.domain.model.enums.UserRole;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String fullName;

    @Enumerated(EnumType.STRING) // Αποθήκευση ως String στη DB
    @Column(nullable = false)
    private UserRole role;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SubscriptionTier subscriptionTier; // Μετονόμασέ το για να ταιριάζει με το Domain
}