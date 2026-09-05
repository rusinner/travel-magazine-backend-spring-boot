package com.travelmagazine.travelmagazineapi.core.application.usecase;

import com.travelmagazine.travelmagazineapi.core.domain.exception.ResourceNotFoundException;
import com.travelmagazine.travelmagazineapi.core.domain.model.Article;
import com.travelmagazine.travelmagazineapi.core.domain.model.User;
import com.travelmagazine.travelmagazineapi.core.domain.repository.ArticleRepository;
import com.travelmagazine.travelmagazineapi.core.domain.repository.UserRepository;
import com.travelmagazine.travelmagazineapi.core.domain.service.ArticleSecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetProtectedArticleUseCase {
    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;
    private final ArticleSecurityService articleSecurityService;

    public Article execute(String slug, String email) {
        // 1. Φέρνουμε το άρθρο
        Article article = articleRepository.findBySlug(slug)
                .orElseThrow(() -> new ResourceNotFoundException("Article not found"));

        // 2. Εντοπίζουμε τον χρήστη από το email (αν υπάρχει)
        User user = (email != null) ? userRepository.findByEmail(email).orElse(null) : null;

        // 3. Ο "φύλακας" εφαρμόζει τους κανόνες (Premium check κτλ)
        articleSecurityService.filterArticleContent(article, user);

        return article;
    }
}