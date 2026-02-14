package com.travelmagazine.travelmagazineapi.core.application.usecase;

import com.travelmagazine.travelmagazineapi.core.domain.model.Category;
import com.travelmagazine.travelmagazineapi.core.domain.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateCategoryUseCase {

    private final CategoryRepository categoryRepository;

    public Category execute(String name, String description) {
        // Εδώ βάζουμε τη λογική μας: π.χ. δημιουργία αυτόματου slug
        String slug = name.toLowerCase()
                .trim()
                .replace(" ", "-")
                .replaceAll("[^a-z0-9-]", "");

        Category category = new Category();
        category.setName(name);
        category.setDescription(description);
        category.setSlug(slug);

        return categoryRepository.save(category);
    }
}