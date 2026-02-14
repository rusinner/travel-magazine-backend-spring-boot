package com.travelmagazine.travelmagazineapi.infrastructure.persistence.repository;

import com.travelmagazine.travelmagazineapi.infrastructure.persistence.entity.NewsletterSubscriberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface JpaNewsletterRepository extends JpaRepository<NewsletterSubscriberEntity, Long> {
    Optional<NewsletterSubscriberEntity> findByEmail(String email);
}