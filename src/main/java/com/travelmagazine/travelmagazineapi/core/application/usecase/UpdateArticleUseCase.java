package com.travelmagazine.travelmagazineapi.core.application.usecase;

import com.travelmagazine.travelmagazineapi.core.domain.model.Article;
import com.travelmagazine.travelmagazineapi.core.domain.model.Category;
import com.travelmagazine.travelmagazineapi.core.domain.repository.ArticleRepository;
import com.travelmagazine.travelmagazineapi.core.domain.repository.CategoryRepository;
import com.travelmagazine.travelmagazineapi.core.domain.service.ArticleSecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateArticleUseCase {
    private final ArticleRepository articleRepository;
    private final CategoryRepository categoryRepository;
    private final ArticleSecurityService articleSecurityService;

    public Article execute(Long id, String title, String content, String mainImageUrl, Long categoryId, boolean premium) {
        // 1. Βρίσκουμε το υπάρχον άρθρο
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Article not found"));

        // 2. Ενημερώνουμε τα βασικά πεδία
        article.setTitle(title);
        article.setContent(content);
        article.setMainImageUrl(mainImageUrl);
        article.setPremium(premium);

        // 3. Update Slug (SEO Best Practice: Συγχρονισμός με τον τίτλο)
        String cleanSlug = title.toLowerCase()
                .replaceAll("[^a-z0-9\\s]", "")
                .trim()
                .replaceAll("\\s+", "-");
        article.setSlug(cleanSlug);

        // 4. Ενημέρωση κατηγορίας αν έχει αλλάξει
        if (categoryId != null) {
            Category category = categoryRepository.findById(categoryId)
                    .orElseThrow(() -> new RuntimeException("Category not found"));
            article.setCategory(category);
        }

        // 5. Αποθήκευση και απευθείας επιστροφή (Fix redundant variable warning)
        return articleRepository.save(article);
    }
}