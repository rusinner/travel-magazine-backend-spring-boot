package com.travelmagazine.travelmagazineapi.core.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    private Long id;
    private String name;
    private String slug;
    private String description;
}