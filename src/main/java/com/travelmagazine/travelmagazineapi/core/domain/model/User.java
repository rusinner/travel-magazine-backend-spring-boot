package com.travelmagazine.travelmagazineapi.core.domain.model;

import com.travelmagazine.travelmagazineapi.core.domain.model.enums.SubscriptionTier;
import com.travelmagazine.travelmagazineapi.core.domain.model.enums.UserRole;
import lombok.*;

@Data @AllArgsConstructor @NoArgsConstructor
public class User {
    private Long id;
    private String email;
    private String password;
    private String fullName;
    private UserRole role; // Αλλαγή από String σε UserRole
    private SubscriptionTier subscriptionTier; // Αλλαγή από String σε SubscriptionTier
}