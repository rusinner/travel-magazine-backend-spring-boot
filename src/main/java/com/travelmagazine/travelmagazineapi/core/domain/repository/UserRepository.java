package com.travelmagazine.travelmagazineapi.core.domain.repository;

import com.travelmagazine.travelmagazineapi.core.domain.model.User;
import java.util.Optional;

public interface UserRepository {
    User save(User user);
    Optional<User> findByEmail(String email);
    Optional<User> findById(Long id);
}