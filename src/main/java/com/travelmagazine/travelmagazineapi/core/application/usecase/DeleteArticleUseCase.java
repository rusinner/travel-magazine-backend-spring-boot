package com.travelmagazine.travelmagazineapi.core.application.usecase;

import com.travelmagazine.travelmagazineapi.core.domain.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteArticleUseCase {
    private final ArticleRepository articleRepository;

    public void execute(Long id) {
        if (!articleRepository.existsById(id)) {
            throw new RuntimeException("Article not found");
        }
        articleRepository.deleteById(id);
    }
}