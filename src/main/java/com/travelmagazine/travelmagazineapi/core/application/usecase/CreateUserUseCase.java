package com.travelmagazine.travelmagazineapi.core.application.usecase;

import com.travelmagazine.travelmagazineapi.core.domain.model.User;
import com.travelmagazine.travelmagazineapi.core.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateUserUseCase {

    private final UserRepository userRepository;

    public User execute(User user) {
        // Εδώ αργότερα θα ελέγχουμε αν το email υπάρχει ήδη
        // και θα κρυπτογραφούμε τον κωδικό.
        return userRepository.save(user);
    }
}