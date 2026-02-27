package com.travelmagazine.travelmagazineapi.core.application.usecase;

import com.travelmagazine.travelmagazineapi.core.domain.model.Article;
import com.travelmagazine.travelmagazineapi.core.domain.model.User;
import com.travelmagazine.travelmagazineapi.core.domain.repository.ArticleRepository;
import com.travelmagazine.travelmagazineapi.core.domain.repository.UserRepository;
import com.travelmagazine.travelmagazineapi.core.domain.service.ArticleSecurityService; // Το νέο service
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllArticlesUseCase {
    private final ArticleRepository articleRepository;
    private final UserRepository userRepository; // Χρειάζεται για να βρούμε τον χρήστη
    private final ArticleSecurityService articleSecurityService; // Ο "φύλακας"

    public List<Article> execute(Long userId) {
        // 1. Φέρνουμε όλα τα άρθρα από τη βάση
        List<Article> articles = articleRepository.findAll();

        // 2. Βρίσκουμε τον χρήστη αν υπάρχει ID, αλλιώς null (Guest)
        User user = (userId != null) ? userRepository.findById(userId).orElse(null) : null;

        // 3. Εφαρμόζουμε το φιλτράρισμα σε όλη τη λίστα
        articleSecurityService.filterArticleList(articles, user);

        return articles;
    }
}