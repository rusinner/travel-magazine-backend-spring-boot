package com.travelmagazine.travelmagazineapi.core.domain.service;

import com.travelmagazine.travelmagazineapi.core.domain.model.Article;
import com.travelmagazine.travelmagazineapi.core.domain.model.User;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ArticleSecurityService {
    public void filterArticleContent(Article article, User user) {
        if (article.isPremium()) {
            if (user == null) {
                article.setContent("🔒 Premium Content - Upgrade to PRO to read.");
                return;
            }

            // 1. Έλεγχος Role: Χρησιμοποιούμε contains για να πιάνει το ADMIN είτε έχει ROLE_ μπροστά είτε όχι
            String roleName = user.getRole().name().toUpperCase();
            boolean isAdmin = roleName.contains("ADMIN");

            // 2. Έλεγχος Tier: Αντίστοιχα για το PRO
            String tierName = user.getSubscriptionTier().name().toUpperCase();
            boolean isPro = tierName.contains("PRO") || tierName.contains("PREMIUM");

            // 3. Έλεγχος Author: Μετατρέπουμε σε long για σίγουρη σύγκριση
            boolean isAuthor = false;
            if (article.getAuthor() != null && user.getId() != null) {
                isAuthor = user.getId().longValue() == article.getAuthor().getId().longValue();
            }
            System.out.println("--- DEBUG START ---");
            System.out.println("Article ID: " + article.getId());
            System.out.println("User Email: " + user.getEmail());
            System.out.println("User Role: '" + user.getRole().name() + "'");
            System.out.println("User Tier: '" + user.getSubscriptionTier().name() + "'");
            System.out.println("User ID: " + user.getId());
            System.out.println("Author ID: " + (article.getAuthor() != null ? article.getAuthor().getId() : "NULL"));
            System.out.println("Result -> isAdmin: " + isAdmin + ", isPro: " + isPro + ", isAuthor: " + isAuthor);
            System.out.println("--- DEBUG END ---");
            // Αν ΔΕΝ είναι τίποτα από τα τρία, τότε μόνο κλειδώνουμε
            if (!isAdmin && !isPro && !isAuthor) {
                article.setContent("🔒 Premium Content - Upgrade to PRO to read.");
            }
        }
    }

    public void filterArticleList(List<Article> articles, User user) {
        articles.forEach(article -> filterArticleContent(article, user));
    }
}