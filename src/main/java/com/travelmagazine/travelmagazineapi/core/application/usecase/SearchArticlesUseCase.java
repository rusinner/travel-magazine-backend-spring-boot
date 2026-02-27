package com.travelmagazine.travelmagazineapi.core.application.usecase;

import com.travelmagazine.travelmagazineapi.core.domain.model.Article;
import com.travelmagazine.travelmagazineapi.core.domain.model.User;
import com.travelmagazine.travelmagazineapi.core.domain.repository.ArticleRepository;
import com.travelmagazine.travelmagazineapi.core.domain.repository.UserRepository;
import com.travelmagazine.travelmagazineapi.core.domain.service.ArticleSecurityService; // Προσθήκη
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchArticlesUseCase {
    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;
    private final ArticleSecurityService articleSecurityService; // Προσθήκη

    public List<Article> execute(String keyword, Long userId) {
        // 1. Εκτέλεση αναζήτησης στη βάση
        List<Article> articles = articleRepository.searchByTitle(keyword);

        // 2. Εντοπισμός του χρήστη
        User user = (userId != null) ? userRepository.findById(userId).orElse(null) : null;

        // 3. Η ΜΑΓΙΚΗ ΓΡΑΜΜΗ: Ο "φύλακας" αναλαμβάνει όλο το loop και τους ελέγχους
        articleSecurityService.filterArticleList(articles, user);

        return articles;
    }
}