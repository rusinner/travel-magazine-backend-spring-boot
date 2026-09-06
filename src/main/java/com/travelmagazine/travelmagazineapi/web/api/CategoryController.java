package com.travelmagazine.travelmagazineapi.web.api;

import com.travelmagazine.travelmagazineapi.core.application.usecase.CreateCategoryUseCase;
import com.travelmagazine.travelmagazineapi.core.application.usecase.GetAllCategoriesUseCase;
import com.travelmagazine.travelmagazineapi.core.domain.model.Category;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
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
    public ResponseEntity<Category> createCategory(@Valid @RequestBody CategoryRequest request) {
        Category savedCategory = createCategoryUseCase.execute(
                request.getName(),
                request.getDescription()
        );
        return ResponseEntity.ok(savedCategory);
    }

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories() {
        return ResponseEntity.ok(getAllCategoriesUseCase.execute());
    }

    @Data
    public static class CategoryRequest {
        @NotBlank
        @Size(max = 150)
        private String name;

        @Size(max = 1000)
        private String description;
    }
}