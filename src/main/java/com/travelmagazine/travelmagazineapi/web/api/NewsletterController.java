package com.travelmagazine.travelmagazineapi.web.api;

import com.travelmagazine.travelmagazineapi.core.application.usecase.SubscribeToNewsletterUseCase;
import com.travelmagazine.travelmagazineapi.core.domain.model.NewsletterSubscriber;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/newsletter")
@RequiredArgsConstructor
public class NewsletterController {

    private final SubscribeToNewsletterUseCase subscribeToNewsletterUseCase;

    @PostMapping("/subscribe")
    public ResponseEntity<NewsletterSubscriber> subscribe(@RequestBody String email) {
        // Σημείωση: Αν στέλνεις σκέτο κείμενο από το Postman, πρόσεχε τα quotes
        return ResponseEntity.ok(subscribeToNewsletterUseCase.execute(email.replace("\"", "")));
    }
}