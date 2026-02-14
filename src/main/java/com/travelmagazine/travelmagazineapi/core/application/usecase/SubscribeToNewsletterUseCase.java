package com.travelmagazine.travelmagazineapi.core.application.usecase;

import com.travelmagazine.travelmagazineapi.core.domain.model.NewsletterSubscriber;
import com.travelmagazine.travelmagazineapi.core.domain.repository.NewsletterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class SubscribeToNewsletterUseCase {
    private final NewsletterRepository newsletterRepository;

    public NewsletterSubscriber execute(String email) {
        // 1. Έλεγχος αν υπάρχει ήδη (Logic)
        if (newsletterRepository.findByEmail(email).isPresent()) {
            throw new RuntimeException("Email already subscribed");
        }

        // 2. Δημιουργία εγγραφής
        NewsletterSubscriber subscriber = new NewsletterSubscriber();
        subscriber.setEmail(email);
        subscriber.setSubscribedAt(LocalDateTime.now());
        subscriber.setActive(true);

        // 3. Εδώ θα έμπαινε η κλήση: mailchimpService.addContact(email);

        return newsletterRepository.save(subscriber);
    }
}