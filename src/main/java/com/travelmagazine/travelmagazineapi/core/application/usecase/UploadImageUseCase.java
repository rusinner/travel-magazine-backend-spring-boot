package com.travelmagazine.travelmagazineapi.core.application.usecase;

import com.travelmagazine.travelmagazineapi.core.domain.exception.InvalidRequestException;
import com.travelmagazine.travelmagazineapi.core.domain.repository.ImageStorageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class UploadImageUseCase {
    private final ImageStorageRepository imageStorageRepository;

    public String execute(MultipartFile file) {
        if (file.isEmpty()) {
            throw new InvalidRequestException("Cannot upload empty file.");
        }
        return imageStorageRepository.store(file);
    }
}