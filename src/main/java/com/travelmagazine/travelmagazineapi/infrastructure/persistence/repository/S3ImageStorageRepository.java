package com.travelmagazine.travelmagazineapi.infrastructure.persistence.repository;

import com.travelmagazine.travelmagazineapi.core.domain.repository.ImageStorageRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@Profile("prod") // <--- Αυτό θα τρέχει ΜΟΝΟ στο Cloud (AWS, Azure κλπ)
public class S3ImageStorageRepository implements ImageStorageRepository {
    @Override
    public String store(MultipartFile file) {
        // Εδώ θα γράψεις τον κώδικα για S3 ΜΙΑ φορά στο μέλλον.
        // Το υπόλοιπο project (Controller/Use Cases) δεν θα καταλάβει τίποτα!
        return "https://your-bucket-name.s3.amazonaws.com/unique-image-url.jpg";
    }
}