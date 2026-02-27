package com.travelmagazine.travelmagazineapi.infrastructure.service;

import com.travelmagazine.travelmagazineapi.core.domain.service.StorageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.*;
import java.util.UUID;

@Service
@Profile("dev")
public class FileSystemStorageService implements StorageService {

    @Value("${storage.location}")
    private String storageLocation;

    @Override
    public String store(MultipartFile file) {
        try {
            String filename = UUID.randomUUID() + "_" + file.getOriginalFilename();
            Path root = Paths.get(storageLocation);
            if (!Files.exists(root)) Files.createDirectories(root);

            Files.copy(file.getInputStream(), root.resolve(filename), StandardCopyOption.REPLACE_EXISTING);
            return filename;
        } catch (IOException e) {
            throw new RuntimeException("Could not store file", e);
        }
    }

    @Override
    public void delete(String filename) {
        try {
            Files.deleteIfExists(Paths.get(storageLocation).resolve(filename));
        } catch (IOException e) {
            throw new RuntimeException("Could not delete file", e);
        }
    }
}