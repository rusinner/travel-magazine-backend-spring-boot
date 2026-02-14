package com.travelmagazine.travelmagazineapi.infrastructure.persistence.repository;

import com.travelmagazine.travelmagazineapi.infrastructure.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface JpaUserRepository extends JpaRepository<UserEntity, Long> {
    // Μόνο η υπογραφή! Το Spring κάνει τα υπόλοιπα.
    Optional<UserEntity> findByEmail(String email);
}