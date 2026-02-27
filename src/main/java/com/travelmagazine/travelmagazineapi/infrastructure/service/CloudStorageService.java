package com.travelmagazine.travelmagazineapi.infrastructure.service;


import com.travelmagazine.travelmagazineapi.core.domain.service.StorageService; // Το Interface που φτιάξαμε
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@Profile("prod")
public class CloudStorageService implements StorageService {

    @Override
    public String store(MultipartFile file) {
        // Εδώ θα μπει ο κώδικας για AWS S3 / Cloudinary
        return "https://cloud-storage.com/bucket/" + file.getOriginalFilename();
    }

    @Override
    public void delete(String filename) {
        /* Cloud delete logic */
    }
}