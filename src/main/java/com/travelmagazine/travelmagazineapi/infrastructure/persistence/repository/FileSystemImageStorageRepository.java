package com.travelmagazine.travelmagazineapi.infrastructure.persistence.repository;

import com.travelmagazine.travelmagazineapi.core.domain.repository.ImageStorageRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.UUID;

@Component
public class FileSystemImageStorageRepository implements ImageStorageRepository {

    @Value("${storage.location}")
    private String storageLocation;

    @Override
    public String store(MultipartFile file) {
        try {
            // Δημιουργία μοναδικού ονόματος
            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();

            // --- PLACEHOLDER ΓΙΑ DEPLOYMENT (π.χ. AWS S3) ---
            // Εδώ στο μέλλον θα μπει ο κώδικας για το Cloud Storage

            Path root = Paths.get(storageLocation);
            if (!Files.exists(root)) {
                Files.createDirectories(root);
            }

            Files.copy(file.getInputStream(), root.resolve(fileName));
            return fileName;

        } catch (IOException e) {
            throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
        }
    }
}