package com.travelmagazine.travelmagazineapi.core.domain.repository;

import com.travelmagazine.travelmagazineapi.core.domain.model.Comment;
import java.util.List;

public interface CommentRepository {
    Comment save(Comment comment);
    List<Comment> findByArticleId(Long articleId);
    void deleteById(Long id);
}