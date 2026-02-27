package com.travelmagazine.travelmagazineapi.core.domain.service;

import com.travelmagazine.travelmagazineapi.core.domain.model.Article;
import com.travelmagazine.travelmagazineapi.core.domain.model.User;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ArticleSecurityService {
    public void filterArticleContent(Article article, User user) {
        if (article.isPremium()) {
            // 1. Είναι Admin;
            boolean isAdmin = user != null && "ADMIN".equals(user.getRole().name());

            // 2. Είναι PRO συνδρομητής;
            boolean isPro = user != null && "PRO".equals(user.getSubscriptionTier().name());

            // 3. Είναι ο συγγραφέας του άρθρου; (Πολύ σημαντικό!)
            boolean isAuthor = user != null && article.getAuthor() != null
                    && user.getId().equals(article.getAuthor().getId());

            // Αν δεν είναι τίποτα από τα παραπάνω, κλείδωσε το περιεχόμενο
            if (!isAdmin && !isPro && !isAuthor) {
                article.setContent("🔒 Premium Content - Upgrade to PRO to read.");
            }
        }
    }

    public void filterArticleList(List<Article> articles, User user) {
        articles.forEach(article -> filterArticleContent(article, user));
    }
}