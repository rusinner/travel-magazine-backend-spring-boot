package com.travelmagazine.travelmagazineapi.core.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    private Long id;
    private String text;
    private User user;    // Ποιος το έγραψε
    private Long articleId; // Σε ποιο άρθρο ανήκει
    private LocalDateTime createdAt;
}