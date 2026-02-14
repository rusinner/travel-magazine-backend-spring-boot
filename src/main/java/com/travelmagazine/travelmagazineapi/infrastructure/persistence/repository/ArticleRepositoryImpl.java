package com.travelmagazine.travelmagazineapi.infrastructure.persistence.repository;

import com.travelmagazine.travelmagazineapi.core.domain.model.Article;
import com.travelmagazine.travelmagazineapi.core.domain.repository.ArticleRepository; // Αυτό το interface πρέπει να υπάρχει στο Domain
import com.travelmagazine.travelmagazineapi.infrastructure.persistence.mapper.ArticleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ArticleRepositoryImpl implements ArticleRepository {

    private final JpaArticleRepository jpaArticleRepository;
    private final ArticleMapper articleMapper;

    @Override
    public Article save(Article article) {
        var entity = articleMapper.toEntity(article);
        var savedEntity = jpaArticleRepository.save(entity);
        return articleMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Article> findById(Long id) {
        return jpaArticleRepository.findById(id)
                .map(articleMapper::toDomain);
    }

    @Override
    public List<Article> findAll() {
        return jpaArticleRepository.findAll().stream()
                .map(articleMapper::toDomain) // Μετατροπή Entity -> Domain
                .toList();
    }
    @Override
    public Optional<Article> findBySlug(String slug) {
        return jpaArticleRepository.findBySlug(slug)
                .map(articleMapper::toDomain);
    }

    @Override
    public void deleteById(Long id) {
        jpaArticleRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return jpaArticleRepository.existsById(id);
    }
}