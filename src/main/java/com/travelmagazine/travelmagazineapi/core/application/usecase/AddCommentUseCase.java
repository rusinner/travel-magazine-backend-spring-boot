package com.travelmagazine.travelmagazineapi.core.application.usecase;

import com.travelmagazine.travelmagazineapi.core.domain.exception.ResourceNotFoundException;
import com.travelmagazine.travelmagazineapi.core.domain.model.Comment;
import com.travelmagazine.travelmagazineapi.core.domain.model.User;
import com.travelmagazine.travelmagazineapi.core.domain.repository.CommentRepository;
import com.travelmagazine.travelmagazineapi.core.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AddCommentUseCase {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    public Comment execute(Long articleId, String text, String userEmail) {
        // 1. Βρίσκουμε τον χρήστη από το email του Token
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        // 2. Δημιουργούμε το νέο σχόλιο
        Comment comment = new Comment();
        comment.setText(text);
        comment.setUser(user);
        comment.setArticleId(articleId);
        comment.setCreatedAt(LocalDateTime.now());

        // 3. Αποθήκευση
        return commentRepository.save(comment);
    }
}