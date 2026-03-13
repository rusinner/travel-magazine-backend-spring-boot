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
public class SearchArticlesUseCase {
    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;
    private final ArticleSecurityService articleSecurityService;

    public List<Article> execute(String keyword, String email) {
        // 1. Εκτέλεση αναζήτησης στη βάση
        List<Article> articles = articleRepository.searchByTitle(keyword);

        // 2. Εντοπισμός του χρήστη από το email
        User user = (email != null) ? userRepository.findByEmail(email).orElse(null) : null;

        // 3. Φιλτράρουμε τη λίστα
        articleSecurityService.filterArticleList(articles, user);

        return articles;
    }
}