package com.travelmagazine.travelmagazineapi.web.api;

import com.travelmagazine.travelmagazineapi.core.application.usecase.SubscribeToNewsletterUseCase;
import com.travelmagazine.travelmagazineapi.core.domain.model.NewsletterSubscriber;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/newsletter")
@RequiredArgsConstructor
@Validated
public class NewsletterController {

    private final SubscribeToNewsletterUseCase subscribeToNewsletterUseCase;

    @PostMapping("/subscribe")
    public ResponseEntity<NewsletterSubscriber> subscribe(@Email @NotBlank @RequestBody String email) {
        // Σημείωση: Αν στέλνεις σκέτο κείμενο από το Postman, πρόσεχε τα quotes
        return ResponseEntity.ok(subscribeToNewsletterUseCase.execute(email.replace("\"", "")));
    }
}