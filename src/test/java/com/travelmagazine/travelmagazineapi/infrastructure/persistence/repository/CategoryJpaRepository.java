package com.travelmagazine.travelmagazineapi.infrastructure.persistence.repository;

import com.travelmagazine.travelmagazineapi.infrastructure.persistence.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryJpaRepository extends JpaRepository<CategoryEntity, Long> {
    // Δεν χρειάζεται να γράψεις τίποτα εδώ μέσα!
    // Κληρονομεί αυτόματα μεθόδους όπως save(), findById(), findAll() κλπ.
}