package com.travelmagazine.travelmagazineapi.core.domain.repository;

import org.springframework.web.multipart.MultipartFile;

public interface ImageStorageRepository {
    String store(MultipartFile file);
}