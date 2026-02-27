package com.travelmagazine.travelmagazineapi.infrastructure.persistence.mapper;

import com.travelmagazine.travelmagazineapi.core.domain.model.User;
import com.travelmagazine.travelmagazineapi.infrastructure.persistence.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toDomain(UserEntity entity) {
        if (entity == null) return null;

        // Χρησιμοποιούμε τον No-Args Constructor και Setters
        User user = new User();
        user.setId(entity.getId());
        user.setEmail(entity.getEmail());
        user.setPassword(entity.getPassword());
        user.setFullName(entity.getFullName());
        user.setRole(entity.getRole());
        user.setSubscriptionTier(entity.getSubscriptionTier());
        user.setCreatedAt(entity.getCreatedAt());

        return user;
    }

    public UserEntity toEntity(User domain) {
        if (domain == null) return null;

        UserEntity entity = new UserEntity();
        entity.setId(domain.getId());
        entity.setEmail(domain.getEmail());
        entity.setPassword(domain.getPassword());
        entity.setFullName(domain.getFullName());
        entity.setRole(domain.getRole());
        entity.setSubscriptionTier(domain.getSubscriptionTier());
        entity.setCreatedAt(domain.getCreatedAt());

        return entity;
    }
}