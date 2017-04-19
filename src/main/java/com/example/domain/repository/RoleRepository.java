package com.example.domain.repository;

import com.example.domain.entity.Role;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Chatzakis Nikolaos
 */
@Repository
public interface RoleRepository extends BaseEntityRepository<Role> {

    Optional<Role> findByName(String name);
}