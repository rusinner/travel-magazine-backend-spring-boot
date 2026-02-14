package com.travelmagazine.travelmagazineapi.infrastructure.persistence.mapper;

import com.travelmagazine.travelmagazineapi.core.domain.model.NewsletterSubscriber;
import com.travelmagazine.travelmagazineapi.infrastructure.persistence.entity.NewsletterSubscriberEntity;
import org.springframework.stereotype.Component;

@Component
public class NewsletterMapper {

    public NewsletterSubscriber toDomain(NewsletterSubscriberEntity entity) {
        if (entity == null) return null;
        return new NewsletterSubscriber(
                entity.getId(),
                entity.getEmail(),
                entity.getSubscribedAt(),
                entity.isActive()
        );
    }

    public NewsletterSubscriberEntity toEntity(NewsletterSubscriber domain) {
        if (domain == null) return null;
        NewsletterSubscriberEntity entity = new NewsletterSubscriberEntity();
        entity.setId(domain.getId());
        entity.setEmail(domain.getEmail());
        entity.setSubscribedAt(domain.getSubscribedAt());
        entity.setActive(domain.isActive());
        return entity;
    }
}