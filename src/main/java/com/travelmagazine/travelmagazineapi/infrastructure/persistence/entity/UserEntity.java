package com.travelmagazine.travelmagazineapi.infrastructure.persistence.entity;

import com.travelmagazine.travelmagazineapi.core.domain.model.enums.SubscriptionTier;
import com.travelmagazine.travelmagazineapi.core.domain.model.enums.UserRole;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime; // Απαραίτητο import

@Entity
@Table(name = "users")
@Data // Περιλαμβάνει Getter, Setter, RequiredArgsConstructor, ToString, EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
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

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole role;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "subscription_tier") // Καλή πρακτική για τη DB
    private SubscriptionTier subscriptionTier;


//    @Column(nullable = false)
    private LocalDateTime createdAt;

}