package com.travelmagazine.travelmagazineapi.web.dto.response;

public record UserResponse(
        Long id,
        String fullName,
        String email,
        String subscriptionTier
) {}