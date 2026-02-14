package com.travelmagazine.travelmagazineapi.core.domain.repository;

import com.travelmagazine.travelmagazineapi.core.domain.model.Article;
import java.util.Optional;
import java.util.List;

public interface ArticleRepository {
    Article save(Article article);
    Optional<Article> findById(Long id);
    Optional<Article> findBySlug(String slug);
    List<Article> findAll();
    void deleteById(Long id);
    boolean existsById(Long id);
}