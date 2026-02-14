package com.travelmagazine.travelmagazineapi.web.api;

import com.travelmagazine.travelmagazineapi.core.application.usecase.*;
import com.travelmagazine.travelmagazineapi.core.domain.model.Article;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/articles")
@RequiredArgsConstructor
public class ArticleController {

    private final CreateArticleUseCase createArticleUseCase;
    private final GetAllArticlesUseCase getAllArticlesUseCase;
    private final UpdateArticleUseCase updateArticleUseCase;
    private final DeleteArticleUseCase deleteArticleUseCase;

    // ΠΡΟΣΘΗΚΗ: Το νέο Use Case που ελέγχει τα Premium δικαιώματα
    private final GetProtectedArticleUseCase getProtectedArticleUseCase;

    // 1. Create
    @PostMapping
    public ResponseEntity<Article> create(@RequestBody ArticleRequest request) {
        return ResponseEntity.ok(createArticleUseCase.execute(
                request.getTitle(), request.getContent(), request.getMainImageUrl(),
                request.getCategoryId(), request.getAuthorId(), request.isPremium()
        ));
    }

    // 2. Read All
    @GetMapping
    public ResponseEntity<List<Article>> getAll() {
        return ResponseEntity.ok(getAllArticlesUseCase.execute());
    }

    // 3. Read One (SEO Friendly & Protected)
    // Τώρα δέχεται προαιρετικά το userId για να δει αν θα ξεκλειδώσει το περιεχόμενο
    @GetMapping("/{slug}")
    public ResponseEntity<Article> getBySlug(
            @PathVariable String slug,
            @RequestParam(required = false) Long userId) {

        return ResponseEntity.ok(getProtectedArticleUseCase.execute(slug, userId));
    }

    // 4. Update
    @PutMapping("/{id}")
    public ResponseEntity<Article> update(@PathVariable Long id, @RequestBody ArticleRequest request) {
        return ResponseEntity.ok(updateArticleUseCase.execute(
                id, request.getTitle(), request.getContent(), request.getMainImageUrl(),
                request.getCategoryId(), request.isPremium()
        ));
    }

    // 5. Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        deleteArticleUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }

    @Data
    public static class ArticleRequest {
        private String title;
        private String content;
        private String mainImageUrl;
        private Long categoryId;
        private Long authorId;
        private boolean isPremium;
    }
}