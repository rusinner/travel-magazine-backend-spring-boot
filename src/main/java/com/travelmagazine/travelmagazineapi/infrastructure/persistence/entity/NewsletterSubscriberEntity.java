package com.travelmagazine.travelmagazineapi.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "newsletter_subscribers")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class NewsletterSubscriberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    private LocalDateTime subscribedAt;
    private boolean active = true;
}