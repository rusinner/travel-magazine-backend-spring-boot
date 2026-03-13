package com.travelmagazine.travelmagazineapi.infrastructure.persistence.repository;

import com.travelmagazine.travelmagazineapi.core.domain.model.Comment;
import com.travelmagazine.travelmagazineapi.core.domain.model.User;
import com.travelmagazine.travelmagazineapi.core.domain.repository.CommentRepository;
import com.travelmagazine.travelmagazineapi.infrastructure.persistence.entity.CommentEntity;
import com.travelmagazine.travelmagazineapi.infrastructure.persistence.entity.ArticleEntity;
import com.travelmagazine.travelmagazineapi.infrastructure.persistence.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class SqlCommentRepository implements CommentRepository {

    private final JpaCommentRepository jpaCommentRepository;
    private final JpaArticleRepository jpaArticleRepository;
    private final JpaUserRepository jpaUserRepository;

    @Override
    public Comment save(Comment comment) {
        CommentEntity entity = new CommentEntity();
        entity.setText(comment.getText());
        entity.setCreatedAt(comment.getCreatedAt());

        // Σύνδεση με Article Entity
        ArticleEntity articleEntity = jpaArticleRepository.findById(comment.getArticleId())
                .orElseThrow(() -> new RuntimeException("Article not found"));
        entity.setArticle(articleEntity);

        // Σύνδεση με User Entity
        UserEntity userEntity = jpaUserRepository.findByEmail(comment.getUser().getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));
        entity.setUser(userEntity);

        CommentEntity saved = jpaCommentRepository.save(entity);
        comment.setId(saved.getId());
        return comment;
    }

    @Override
    public List<Comment> findByArticleId(Long articleId) {
        return jpaCommentRepository.findByArticleId(articleId).stream()
                .map(entity -> {
                    // Μετατροπή του UserEntity σε User domain model
                    User userModel = new User();
                    userModel.setId(entity.getUser().getId());
                    userModel.setEmail(entity.getUser().getEmail());
                    userModel.setFullName(entity.getUser().getFullName());
                    // Πρόσθεσε role/tier αν τα χρειάζεσαι στο frontend των σχολίων

                    return new Comment(
                            entity.getId(),
                            entity.getText(),
                            userModel, // Τώρα ο χρήστης δεν είναι πια null!
                            entity.getArticle().getId(),
                            entity.getCreatedAt()
                    );
                }).collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        jpaCommentRepository.deleteById(id);
    }
}