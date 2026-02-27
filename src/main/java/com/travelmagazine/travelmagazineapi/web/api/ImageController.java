package com.travelmagazine.travelmagazineapi.web.api;

import com.travelmagazine.travelmagazineapi.core.application.usecase.UploadImageUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value; // Προσθήκη
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.Map;

@RestController
@RequestMapping("/api/images")
@RequiredArgsConstructor
public class ImageController {

    private final UploadImageUseCase uploadImageUseCase;

    // Τραβάμε το Base URL από τις ρυθμίσεις (application.properties)
    @Value("${storage.base-url}")
    private String baseUrl;

    @PostMapping("/upload")
    public ResponseEntity<Map<String, String>> upload(@RequestParam("file") MultipartFile file) {
        // Το UseCase επιστρέφει είτε το filename (local) είτε ολόκληρο το URL (cloud)
        String result = uploadImageUseCase.execute(file);

        // ΕΞΥΠΝΗ ΛΟΓΙΚΗ:
        // Αν το αποτέλεσμα ξεκινάει ήδη με http, είναι από Cloud, οπότε το στέλνουμε ως έχει.
        // Αν είναι απλό όνομα αρχείου, κολλάμε μπροστά το baseUrl (π.χ. /uploads/)
        String finalUrl = result.startsWith("http") ? result : baseUrl + result;

        return ResponseEntity.ok(Map.of("url", finalUrl));
    }
}