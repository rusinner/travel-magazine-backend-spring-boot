package com.travelmagazine.travelmagazineapi.core.application.usecase;

import com.travelmagazine.travelmagazineapi.core.domain.model.Article;
import com.travelmagazine.travelmagazineapi.core.domain.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllArticlesUseCase {
    private final ArticleRepository articleRepository;

    public List<Article> execute() {
        return articleRepository.findAll();
    }
}