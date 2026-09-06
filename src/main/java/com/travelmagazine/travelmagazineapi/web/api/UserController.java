package com.travelmagazine.travelmagazineapi.web.api;

import com.travelmagazine.travelmagazineapi.core.application.usecase.RegisterUserUseCase;
import com.travelmagazine.travelmagazineapi.core.domain.model.User;
import com.travelmagazine.travelmagazineapi.web.dto.response.UserResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    // Χρησιμοποιούμε το RegisterUserUseCase που φτιάξαμε με το BCrypt
    private final RegisterUserUseCase registerUserUseCase;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@Valid @RequestBody RegisterRequest request) {
        // Εκτελούμε την εγγραφή
        User user = registerUserUseCase.execute(
                request.getFullName(),
                request.getEmail(),
                request.getPassword()
        );

        // Επιστρέφουμε UserResponse (DTO) για να ΜΗΝ φανεί το password στο JSON
        return ResponseEntity.ok(new UserResponse(
                user.getId(),
                user.getFullName(),
                user.getEmail(),
                user.getSubscriptionTier().name()
        ));
    }

    // Το Request DTO για την εγγραφή
    @Data
    public static class RegisterRequest {
        @NotBlank
        @Size(min = 2, max = 100)
        private String fullName;

        @NotBlank
        @Email
        private String email;

        @NotBlank
        @Size(min = 8, max = 100)
        private String password;
    }
}