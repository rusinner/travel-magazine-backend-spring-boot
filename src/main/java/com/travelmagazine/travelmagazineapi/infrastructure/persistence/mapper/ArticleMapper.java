package com.travelmagazine.travelmagazineapi.infrastructure.persistence.mapper;

import com.travelmagazine.travelmagazineapi.core.domain.model.Article;
import com.travelmagazine.travelmagazineapi.infrastructure.persistence.entity.ArticleEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ArticleMapper {

    private final UserMapper userMapper;
    private final CategoryMapper categoryMapper;

    public Article toDomain(ArticleEntity entity) {
        if (entity == null) return null;
        Article domain = new Article();
        domain.setId(entity.getId());
        domain.setTitle(entity.getTitle());
        domain.setSlug(entity.getSlug());
        domain.setContent(entity.getContent());
        domain.setMainImageUrl(entity.getMainImageUrl());
        domain.setPremium(entity.isPremium());
        domain.setCategory(categoryMapper.toDomain(entity.getCategory()));
        domain.setAuthor(userMapper.toDomain(entity.getAuthor()));
        domain.setCreatedAt(entity.getCreatedAt());
        return domain;
    }

    public ArticleEntity toEntity(Article domain) {
        if (domain == null) return null;
        ArticleEntity entity = new ArticleEntity();
        entity.setId(domain.getId());
        entity.setTitle(domain.getTitle());
        entity.setSlug(domain.getSlug());
        entity.setContent(domain.getContent());
        entity.setMainImageUrl(domain.getMainImageUrl());
        entity.setPremium(domain.isPremium());
        entity.setCategory(categoryMapper.toEntity(domain.getCategory()));
        entity.setAuthor(userMapper.toEntity(domain.getAuthor()));
        entity.setCreatedAt(domain.getCreatedAt());
        return entity;
    }
}