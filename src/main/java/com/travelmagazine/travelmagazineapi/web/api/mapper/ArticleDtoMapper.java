package com.travelmagazine.travelmagazineapi.web.api.mapper;

import com.travelmagazine.travelmagazineapi.core.domain.model.Article;
import com.travelmagazine.travelmagazineapi.core.domain.model.User;
import com.travelmagazine.travelmagazineapi.web.dto.response.ArticleResponse;
import com.travelmagazine.travelmagazineapi.web.dto.response.UserResponse;
import org.springframework.stereotype.Component;

@Component
public class ArticleDtoMapper {

    public ArticleResponse toResponse(Article article) {
        return new ArticleResponse(
                article.getId(),
                article.getTitle(),
                article.getContent(),
                article.getSlug(),
                article.getMainImageUrl(),
                article.isPremium(),
                article.getCategory() != null ? article.getCategory().getName() : null,
                toUserResponse(article.getAuthor()),
                article.getCreatedAt()
        );
    }

    private UserResponse toUserResponse(User user) {
        if (user == null) return null;
        return new UserResponse(
                user.getId(),
                user.getFullName(),
                user.getEmail(),
                user.getSubscriptionTier().name()
        );
    }
}