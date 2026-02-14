package com.travelmagazine.travelmagazineapi.infrastructure.persistence.mapper;

import com.travelmagazine.travelmagazineapi.core.domain.model.Category;
import com.travelmagazine.travelmagazineapi.infrastructure.persistence.entity.CategoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    Category toDomain(CategoryEntity entity);
    CategoryEntity toEntity(Category domain);
}