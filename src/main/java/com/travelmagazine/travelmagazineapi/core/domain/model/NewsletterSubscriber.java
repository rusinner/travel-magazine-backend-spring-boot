package com.travelmagazine.travelmagazineapi.core.domain.model;

import lombok.*;
import java.time.LocalDateTime;

@Data @AllArgsConstructor @NoArgsConstructor
public class NewsletterSubscriber {
    private Long id;
    private String email;
    private LocalDateTime subscribedAt;
    private boolean active;
}