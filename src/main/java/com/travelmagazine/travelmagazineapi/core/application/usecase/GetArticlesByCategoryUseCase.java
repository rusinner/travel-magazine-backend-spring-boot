package com.travelmagazine.travelmagazineapi.core.application.usecase;

import com.travelmagazine.travelmagazineapi.core.domain.model.Article;
import com.travelmagazine.travelmagazineapi.core.domain.model.User;
import com.travelmagazine.travelmagazineapi.core.domain.repository.ArticleRepository;
import com.travelmagazine.travelmagazineapi.core.domain.repository.UserRepository;
import com.travelmagazine.travelmagazineapi.core.domain.service.ArticleSecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GetArticlesByCategoryUseCase {
    private final ArticleRepository articleRepository;
    private final UserRepository userRepository; // Προσθήκη
    private final ArticleSecurityService articleSecurityService; // Προσθήκη

    public List<Article> execute(String categorySlug, Long userId) {
        // 1. Φέρνουμε τα άρθρα της συγκεκριμένης κατηγορίας
        List<Article> articles = articleRepository.findByCategorySlug(categorySlug);

        // 2. Εντοπίζουμε τον χρήστη
        User user = (userId != null) ? userRepository.findById(userId).orElse(null) : null;

        // 3. Φιλτράρουμε το περιεχόμενο (κλείδωμα Premium αν χρειάζεται)
        articleSecurityService.filterArticleList(articles, user);

        return articles;
    }
}