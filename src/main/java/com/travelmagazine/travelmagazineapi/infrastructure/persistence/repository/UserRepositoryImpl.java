package com.travelmagazine.travelmagazineapi.infrastructure.persistence.repository;

import com.travelmagazine.travelmagazineapi.core.domain.model.User;
import com.travelmagazine.travelmagazineapi.core.domain.repository.UserRepository;
import com.travelmagazine.travelmagazineapi.infrastructure.persistence.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final JpaUserRepository jpaUserRepository;
    private final UserMapper userMapper;

    @Override
    public User save(User user) {
        var entity = userMapper.toEntity(user);
        var savedEntity = jpaUserRepository.save(entity);
        return userMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        // Καλούμε το JpaRepository και μετατρέπουμε το Entity σε Domain User
        return jpaUserRepository.findByEmail(email)
                .map(userMapper::toDomain);
    }

    @Override
    public Optional<User> findById(Long id) {
        return jpaUserRepository.findById(id)
                .map(userMapper::toDomain);
    }
}