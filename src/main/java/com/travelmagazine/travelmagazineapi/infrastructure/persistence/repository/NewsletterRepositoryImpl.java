package com.travelmagazine.travelmagazineapi.infrastructure.persistence.repository;

import com.travelmagazine.travelmagazineapi.core.domain.model.NewsletterSubscriber;
import com.travelmagazine.travelmagazineapi.core.domain.repository.NewsletterRepository;
import com.travelmagazine.travelmagazineapi.infrastructure.persistence.mapper.NewsletterMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class NewsletterRepositoryImpl implements NewsletterRepository {

    private final JpaNewsletterRepository jpaNewsletterRepository;
    private final NewsletterMapper newsletterMapper;

    @Override
    public NewsletterSubscriber save(NewsletterSubscriber subscriber) {
        var entity = newsletterMapper.toEntity(subscriber);
        var savedEntity = jpaNewsletterRepository.save(entity);
        return newsletterMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<NewsletterSubscriber> findByEmail(String email) {
        return jpaNewsletterRepository.findByEmail(email)
                .map(newsletterMapper::toDomain);
    }
}