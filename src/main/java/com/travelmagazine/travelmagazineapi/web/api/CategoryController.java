package com.travelmagazine.travelmagazineapi.web.api;

import com.travelmagazine.travelmagazineapi.core.application.usecase.CreateCategoryUseCase;
import com.travelmagazine.travelmagazineapi.core.application.usecase.GetAllCategoriesUseCase;
import com.travelmagazine.travelmagazineapi.core.domain.model.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CreateCategoryUseCase createCategoryUseCase;
    private final GetAllCategoriesUseCase getAllCategoriesUseCase;

    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        Category savedCategory = createCategoryUseCase.execute(
                category.getName(),
                category.getDescription()
        );
        return ResponseEntity.ok(savedCategory);
    }

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories() {
        return ResponseEntity.ok(getAllCategoriesUseCase.execute());
    }
}