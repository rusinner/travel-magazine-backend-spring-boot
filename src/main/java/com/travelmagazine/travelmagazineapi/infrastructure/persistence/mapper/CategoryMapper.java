package com.travelmagazine.travelmagazineapi.infrastructure.persistence.mapper;

import com.travelmagazine.travelmagazineapi.core.domain.model.Category;
import com.travelmagazine.travelmagazineapi.infrastructure.persistence.entity.CategoryEntity;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    // Από Βάση (Entity) -> Στον Κώδικα (Domain)
    public Category toDomain(CategoryEntity entity) {
        if (entity == null) return null;
        return new Category(
                entity.getId(),
                entity.getName(),
                entity.getSlug(),
                entity.getDescription()
        );
    }

    // Από Κώδικα (Domain) -> Στη Βάση (Entity)
    public CategoryEntity toEntity(Category domain) {
        if (domain == null) return null;
        CategoryEntity entity = new CategoryEntity();
        entity.setId(domain.getId());
        entity.setName(domain.getName());
        entity.setSlug(domain.getSlug());
        entity.setDescription(domain.getDescription());
        return entity;
    }
}