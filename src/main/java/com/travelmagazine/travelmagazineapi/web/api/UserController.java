package com.travelmagazine.travelmagazineapi.web.api;

import com.travelmagazine.travelmagazineapi.core.application.usecase.CreateUserUseCase;
import com.travelmagazine.travelmagazineapi.core.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final CreateUserUseCase createUserUseCase;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        return ResponseEntity.ok(createUserUseCase.execute(user));
    }
}