package com.travelmagazine.travelmagazineapi.core.domain.service;

import org.springframework.web.multipart.MultipartFile;

public interface StorageService {
    String store(MultipartFile file); // Επιστρέφει το όνομα/URL του αρχείου
    void delete(String filename);
}