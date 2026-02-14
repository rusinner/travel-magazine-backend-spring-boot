package com.travelmagazine.travelmagazineapi.infrastructure.persistence.repository;

import com.travelmagazine.travelmagazineapi.core.domain.model.Category;
import com.travelmagazine.travelmagazineapi.core.domain.repository.CategoryRepository;
import com.travelmagazine.travelmagazineapi.infrastructure.persistence.mapper.CategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CategoryRepositoryImpl implements CategoryRepository {

    private final JpaCategoryRepository jpaCategoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public Category save(Category category) {
        // Domain -> Entity
        var entity = categoryMapper.toEntity(category);
        // Save to DB
        var savedEntity = jpaCategoryRepository.save(entity);
        // Entity -> Domain
        return categoryMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Category> findById(Long id) {
        return jpaCategoryRepository.findById(id)
                .map(categoryMapper::toDomain);
    }

    @Override
    public List<Category> findAll() {
        return jpaCategoryRepository.findAll() // Παίρνει τα Entities από τη βάση
                .stream()
                .map(categoryMapper::toDomain) // Τα μετατρέπει σε Domain Models
                .toList();
    }
}