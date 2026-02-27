package com.travelmagazine.travelmagazineapi.web.api;

import com.travelmagazine.travelmagazineapi.core.application.usecase.*;
import com.travelmagazine.travelmagazineapi.core.domain.model.Article;
import com.travelmagazine.travelmagazineapi.web.api.mapper.ArticleDtoMapper;
import com.travelmagazine.travelmagazineapi.web.dto.response.ArticleResponse;
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
    private final GetArticlesByCategoryUseCase getArticlesByCategoryUseCase;
    private final SearchArticlesUseCase searchArticlesUseCase;
    private final GetProtectedArticleUseCase getProtectedArticleUseCase;

    // Προσθήκη του Mapper για τη μετατροπή σε DTO
    private final ArticleDtoMapper articleDtoMapper;

    // 1. Create
    @PostMapping
    public ResponseEntity<ArticleResponse> create(@RequestBody ArticleRequest request) {
        Article article = createArticleUseCase.execute(
                request.getTitle(), request.getContent(), request.getMainImageUrl(),
                request.getCategoryId(), request.getAuthorId(), request.isPremium()
        );
        return ResponseEntity.ok(articleDtoMapper.toResponse(article));
    }

    // 2. Read All
    @GetMapping
    public ResponseEntity<List<ArticleResponse>> getAll(@RequestParam(required = false) Long userId) {
        List<ArticleResponse> responses = getAllArticlesUseCase.execute(userId).stream()
                .map(articleDtoMapper::toResponse)
                .toList();
        return ResponseEntity.ok(responses);
    }

    // 3. Read One (SEO Friendly & Protected)
    @GetMapping("/{slug}")
    public ResponseEntity<ArticleResponse> getBySlug(
            @PathVariable String slug,
            @RequestParam(required = false) Long userId) {

        Article article = getProtectedArticleUseCase.execute(slug, userId);
        return ResponseEntity.ok(articleDtoMapper.toResponse(article));
    }

    // 4. Update
    @PutMapping("/{id}")
    public ResponseEntity<ArticleResponse> update(@PathVariable Long id, @RequestBody ArticleRequest request) {
        Article article = updateArticleUseCase.execute(
                id, request.getTitle(), request.getContent(), request.getMainImageUrl(),
                request.getCategoryId(), request.isPremium()
        );
        return ResponseEntity.ok(articleDtoMapper.toResponse(article));
    }

    // 5. Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        deleteArticleUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }

    // 6. Get By Category
    @GetMapping("/category/{categorySlug}")
    public ResponseEntity<List<ArticleResponse>> getByCategory(
            @PathVariable String categorySlug,
            @RequestParam(required = false) Long userId) {

        List<ArticleResponse> responses = getArticlesByCategoryUseCase.execute(categorySlug, userId).stream()
                .map(articleDtoMapper::toResponse)
                .toList();
        return ResponseEntity.ok(responses);
    }

    // 7. Search
    @GetMapping("/search")
    public ResponseEntity<List<ArticleResponse>> search(
            @RequestParam String q,
            @RequestParam(required = false) Long userId) {

        List<ArticleResponse> responses = searchArticlesUseCase.execute(q, userId).stream()
                .map(articleDtoMapper::toResponse)
                .toList();
        return ResponseEntity.ok(responses);
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