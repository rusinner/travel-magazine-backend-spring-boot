package com.travelmagazine.travelmagazineapi.web.api;

import com.travelmagazine.travelmagazineapi.core.application.usecase.AddCommentUseCase;
import com.travelmagazine.travelmagazineapi.core.application.usecase.GetCommentsByArticleUseCase;
import com.travelmagazine.travelmagazineapi.core.domain.model.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/articles/{articleId}/comments")
@RequiredArgsConstructor
public class CommentController {

    private final AddCommentUseCase addCommentUseCase;
    private final GetCommentsByArticleUseCase getCommentsByArticleUseCase;

    // 1. Προσθήκη Σχολίου (Μόνο για συνδεδεμένους)
    @PostMapping
    public ResponseEntity<CommentResponse> addComment(
            @PathVariable Long articleId,
            @RequestBody CommentRequest request,
            Principal principal) {

        if (principal == null) {
            return ResponseEntity.status(401).build();
        }

        Comment comment = addCommentUseCase.execute(articleId, request.getText(), principal.getName());

        return ResponseEntity.ok(new CommentResponse(
                comment.getId(),
                comment.getText(),
                principal.getName()
        ));
    }

    // 2. Εμφάνιση Σχολίων (Δημόσιο)
    @GetMapping
    public ResponseEntity<List<CommentResponse>> getComments(@PathVariable Long articleId) {
        List<CommentResponse> responses = getCommentsByArticleUseCase.execute(articleId).stream()
                .map(c -> new CommentResponse(
                        c.getId(),
                        c.getText(),
                        c.getUser() != null ? c.getUser().getEmail() : "Anonymous"
                ))
                .toList();
        return ResponseEntity.ok(responses);
    }

    // --- DTOs (Data Transfer Objects) ---

    @Data
    @NoArgsConstructor
    public static class CommentRequest {
        private String text;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CommentResponse {
        private Long id;
        private String text;
        private String userEmail;
    }
}