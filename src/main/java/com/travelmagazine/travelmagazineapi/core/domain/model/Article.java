package com.travelmagazine.travelmagazineapi.core.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Article {
    private Long id;
    private String title;
    private String content;
    private String mainImageUrl;
    private String slug;
    private boolean isPremium;
    private Category category;
    private User author;
    private LocalDateTime createdAt;
}