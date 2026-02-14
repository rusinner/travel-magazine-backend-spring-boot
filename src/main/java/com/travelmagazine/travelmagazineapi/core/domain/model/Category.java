package com.travelmagazine.travelmagazineapi.core.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Αυτό φτιάχνει αυτόματα τα getId, getName, κλπ.
@AllArgsConstructor // Αυτό φτιάχνει τον constructor με όλα τα ορίσματα
@NoArgsConstructor  // Αυτό φτιάχνει τον default constructor (χωρίς ορίσματα)
public class Category {
    private Long id;
    private String name;
    private String slug;
    private String description;
}