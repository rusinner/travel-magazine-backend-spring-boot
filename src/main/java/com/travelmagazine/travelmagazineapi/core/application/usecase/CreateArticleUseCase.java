package com.travelmagazine.travelmagazineapi.core.application.usecase;

import com.travelmagazine.travelmagazineapi.core.domain.model.Article;
import com.travelmagazine.travelmagazineapi.core.domain.model.Category;
import com.travelmagazine.travelmagazineapi.core.domain.model.User;
import com.travelmagazine.travelmagazineapi.core.domain.repository.ArticleRepository;
import com.travelmagazine.travelmagazineapi.core.domain.repository.CategoryRepository;
import com.travelmagazine.travelmagazineapi.core.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CreateArticleUseCase {

    private final ArticleRepository articleRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    // Προσθέσαμε το String mainImageUrl στις παραμέτρους παρακάτω
    public Article execute(String title, String content, String mainImageUrl, Long categoryId, String authorEmail, boolean premium) {

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        User author = userRepository.findByEmail(authorEmail)
                .orElseThrow(() -> new RuntimeException("Author not found"));

        Article article = new Article();
        article.setTitle(title);
        article.setContent(content);
        article.setMainImageUrl(mainImageUrl); // Τώρα η μεταβλητή αναγνωρίζεται!
        article.setCategory(category);
        article.setAuthor(author);
        article.setPremium(premium);
        article.setCreatedAt(LocalDateTime.now());

        // Δημιουργία slug για SEO
        String cleanSlug = title.toLowerCase()
                .replaceAll("[^a-z0-9\\s]", "") // Αφαιρεί τα πάντα εκτός από γράμματα και αριθμούς
                .trim()
                .replaceAll("\\s+", "-"); // Μετατρέπει τα κενά σε παύλες
        article.setSlug(cleanSlug);

        return articleRepository.save(article);
    }
}