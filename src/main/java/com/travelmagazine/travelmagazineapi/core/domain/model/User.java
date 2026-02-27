package com.travelmagazine.travelmagazineapi.core.domain.model;

import com.travelmagazine.travelmagazineapi.core.domain.model.enums.SubscriptionTier;
import com.travelmagazine.travelmagazineapi.core.domain.model.enums.UserRole;
import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Long id;
    private String email;
    private String password;
    private String fullName;
    private UserRole role;
    private SubscriptionTier subscriptionTier;


    private LocalDateTime createdAt;
}