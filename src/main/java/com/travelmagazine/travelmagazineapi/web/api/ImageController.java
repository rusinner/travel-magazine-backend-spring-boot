package com.travelmagazine.travelmagazineapi.web.api;

import com.travelmagazine.travelmagazineapi.core.application.usecase.UploadImageUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.Map;

@RestController
@RequestMapping("/api/images")
@RequiredArgsConstructor
public class ImageController {

    private final UploadImageUseCase uploadImageUseCase;

    @PostMapping("/upload")
    public ResponseEntity<Map<String, String>> upload(@RequestParam("file") MultipartFile file) {
        String fileName = uploadImageUseCase.execute(file);
        // Επιστρέφουμε το URL που θα χρησιμοποιήσει το Frontend
        return ResponseEntity.ok(Map.of("url", "/uploads/" + fileName));
    }
}