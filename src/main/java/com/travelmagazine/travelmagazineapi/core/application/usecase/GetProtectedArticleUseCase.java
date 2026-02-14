package com.travelmagazine.travelmagazineapi.core.application.usecase;

import com.travelmagazine.travelmagazineapi.core.domain.model.Article;
import com.travelmagazine.travelmagazineapi.core.domain.model.User;
import com.travelmagazine.travelmagazineapi.core.domain.repository.ArticleRepository;
import com.travelmagazine.travelmagazineapi.core.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetProtectedArticleUseCase {
    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;

    public Article execute(String slug, Long currentUserId) {
        Article article = articleRepository.findBySlug(slug)
                .orElseThrow(() -> new RuntimeException("Article not found"));

        // ΚΑΝΟΝΑΣ 1: Αν ο συγγραφέας το άφησε FREE, το βλέπουν όλοι (Guest & Users)
        if (!article.isPremium()) {
            return article;
        }

        // ΚΑΝΟΝΑΣ 2: Αν είναι Premium, πρέπει να δούμε ΠΟΙΟΣ είναι
        if (currentUserId == null) {
            article.setContent("🔒 This is a Premium article. Please log in to read more.");
            return article;
        }

        User user = userRepository.findById(currentUserId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // ΚΑΝΟΝΑΣ 3: ΔΙΟΡΘΩΜΕΝΟΣ ΕΛΕΓΧΟΣ
        // Χρησιμοποιούμε .name() γιατί το subscriptionTier και το role είναι Enums
        boolean isPro = "PRO".equals(user.getSubscriptionTier().name());
        boolean isAdmin = "ADMIN".equals(user.getRole().name());

        if (!isPro && !isAdmin) {
            article.setContent("🔒 This content is for PRO members only. Upgrade your plan to gain access.");
        }

        return article;
    }
}