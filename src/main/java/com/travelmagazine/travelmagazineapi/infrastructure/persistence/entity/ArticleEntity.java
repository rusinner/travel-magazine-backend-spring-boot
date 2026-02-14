package com.travelmagazine.travelmagazineapi.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "articles")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ArticleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    private String mainImageUrl;

    @Column(nullable = false)
    private boolean isPremium; // true για Pro, false για Free

    @Column(unique = true, nullable = false)
    private String slug;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity category;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private UserEntity author;

    private LocalDateTime createdAt = LocalDateTime.now();
}