package com.example.service;


import com.example.domain.entity.Role;

import java.util.Optional;

/**
 * @author Chatzakis Nikolaos
 */
public interface RoleService extends GenericService<Role> {

    Optional<Role> findByName(String name);
}
