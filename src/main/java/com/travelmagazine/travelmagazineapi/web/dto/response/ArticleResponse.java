package com.travelmagazine.travelmagazineapi.web.dto.response;

import java.time.LocalDateTime;

public record ArticleResponse(
        Long id,
        String title,
        String content,
        String slug,
        String mainImageUrl,
        boolean isPremium,
        String categoryName, // Στέλνουμε μόνο το όνομα της κατηγορίας
        UserResponse author, // Χρησιμοποιούμε το UserResponse (ΟΧΙ το User με το password!)
        LocalDateTime createdAt
) {}