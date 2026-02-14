package com.travelmagazine.travelmagazineapi.infrastructure.persistence.mapper;

import com.travelmagazine.travelmagazineapi.core.domain.model.User;
import com.travelmagazine.travelmagazineapi.infrastructure.persistence.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toDomain(UserEntity entity) {
        if (entity == null) return null;
        return new User(
                entity.getId(),
                entity.getEmail(),
                entity.getPassword(),
                entity.getFullName(),
                entity.getRole(),
                entity.getSubscriptionTier() // Ενημέρωση εδώ
        );
    }

    public UserEntity toEntity(User domain) {
        if (domain == null) return null;
        UserEntity entity = new UserEntity();
        entity.setId(domain.getId());
        entity.setEmail(domain.getEmail());
        entity.setPassword(domain.getPassword());
        entity.setFullName(domain.getFullName());
        entity.setRole(domain.getRole());
        entity.setSubscriptionTier(domain.getSubscriptionTier()); // Ενημέρωση εδώ
        return entity;
    }
}