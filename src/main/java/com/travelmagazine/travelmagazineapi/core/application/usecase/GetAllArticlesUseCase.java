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
public class GetAllArticlesUseCase {
    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;
    private final ArticleSecurityService articleSecurityService;

    public List<Article> execute(String email) {
        // 1. Φέρνουμε όλα τα άρθρα από τη βάση
        List<Article> articles = articleRepository.findAll();

        // 2. Βρίσκουμε τον χρήστη από το email
        User user = (email != null) ? userRepository.findByEmail(email).orElse(null) : null;

        // 3. Εφαρμόζουμε το φιλτράρισμα σε όλη τη λίστα
        articleSecurityService.filterArticleList(articles, user);

        return articles;
    }
}