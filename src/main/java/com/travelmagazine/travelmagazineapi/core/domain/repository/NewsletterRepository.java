package com.travelmagazine.travelmagazineapi.core.domain.repository;

import com.travelmagazine.travelmagazineapi.core.domain.model.NewsletterSubscriber;
import java.util.Optional;

public interface NewsletterRepository {
    NewsletterSubscriber save(NewsletterSubscriber subscriber);
    Optional<NewsletterSubscriber> findByEmail(String email);
}