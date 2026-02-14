package com.travelmagazine.travelmagazineapi.infrastructure.persistence.repository;

import com.travelmagazine.travelmagazineapi.infrastructure.persistence.entity.ArticleEntity; // Το import που έλειπε
import org.springframework.data.jpa.repository.JpaRepository; // Το import για το Spring Data
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaArticleRepository extends JpaRepository<ArticleEntity, Long> {
    Optional<ArticleEntity> findBySlug(String slug);
}