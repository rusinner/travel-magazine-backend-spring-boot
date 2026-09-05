package com.travelmagazine.travelmagazineapi.core.application.usecase;

import com.travelmagazine.travelmagazineapi.core.domain.exception.ConflictException;
import com.travelmagazine.travelmagazineapi.core.domain.model.User;
import com.travelmagazine.travelmagazineapi.core.domain.model.enums.UserRole;
import com.travelmagazine.travelmagazineapi.core.domain.model.enums.SubscriptionTier;
import com.travelmagazine.travelmagazineapi.core.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class RegisterUserUseCase {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User execute(String fullName, String email, String password) {
        // 1. Έλεγχος αν το email χρησιμοποιείται ήδη
        if (userRepository.findByEmail(email).isPresent()) {
            throw new ConflictException("Email already exists!");
        }

        // 2. Δημιουργία του User αντικειμένου (Domain Model)
        User user = new User();
        user.setFullName(fullName);
        user.setEmail(email);

        // 3. Κρυπτογράφηση κωδικού (ΠΟΤΕ plain text στη βάση)
        user.setPassword(passwordEncoder.encode(password));

        // 4. Προεπιλεγμένες τιμές
        user.setRole(UserRole.READER);
        user.setSubscriptionTier(SubscriptionTier.FREE);
        user.setCreatedAt(LocalDateTime.now());

        return userRepository.save(user);
    }
}