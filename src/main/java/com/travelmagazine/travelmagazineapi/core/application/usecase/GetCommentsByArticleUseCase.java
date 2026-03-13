package com.travelmagazine.travelmagazineapi.core.application.usecase;

import com.travelmagazine.travelmagazineapi.core.domain.model.Comment;
import com.travelmagazine.travelmagazineapi.core.domain.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GetCommentsByArticleUseCase {
    private final CommentRepository commentRepository;

    public List<Comment> execute(Long articleId) {
        return commentRepository.findByArticleId(articleId);
    }
}