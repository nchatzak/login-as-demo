package com.example.domain.repository;

import com.example.domain.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Chatzakis Nikolaos
 */
@Repository
public interface UserRepository extends BaseEntityRepository<User> {

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    List<User> findByRoles_Name(String role);
}
