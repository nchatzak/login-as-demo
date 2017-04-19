package com.example.service;

import com.example.domain.entity.User;
import com.example.domain.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @author Chatzakis Nikolaos
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> findOne(String id) {
        return userRepository.findOneById(id);
    }

    @Override
    public User create(User object) {
        return userRepository.save(object);
    }

    @Override
    public User update(String id, User object) {
        userRepository.findOneById(id)
                .orElseThrow(RuntimeException::new);

        if (!Objects.equals(id, object.getId())) delete(id);

        return create(object);
    }

    @Override
    public void delete(String id) {
        userRepository.delete(id);
    }

    @Override
    public List<User> findByRole(String role) {
        return userRepository.findByRoles_Name(role);
    }
}
