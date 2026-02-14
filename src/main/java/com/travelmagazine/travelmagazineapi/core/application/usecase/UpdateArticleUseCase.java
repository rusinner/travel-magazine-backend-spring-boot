package com.travelmagazine.travelmagazineapi.core.application.usecase;

import com.travelmagazine.travelmagazineapi.core.domain.model.Article;
import com.travelmagazine.travelmagazineapi.core.domain.model.Category;
import com.travelmagazine.travelmagazineapi.core.domain.repository.ArticleRepository;
import com.travelmagazine.travelmagazineapi.core.domain.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateArticleUseCase {
    private final ArticleRepository articleRepository;
    private final CategoryRepository categoryRepository;

    public Article execute(Long id, String title, String content, String mainImageUrl, Long categoryId, boolean isPremium) {
        // 1. Βρίσκουμε το υπάρχον άρθρο
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Article not found"));

        // 2. Ενημερώνουμε τα πεδία
        article.setTitle(title);
        article.setContent(content);
        article.setMainImageUrl(mainImageUrl);
        article.setPremium(isPremium);

        // 3. Αν άλλαξε η κατηγορία, τη βρίσκουμε και την ενημερώνουμε
        if (categoryId != null) {
            Category category = categoryRepository.findById(categoryId)
                    .orElseThrow(() -> new RuntimeException("Category not found"));
            article.setCategory(category);
        }

        return articleRepository.save(article);
    }
}