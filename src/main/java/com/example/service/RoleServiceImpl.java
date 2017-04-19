package com.example.service;


import com.example.domain.entity.Role;
import com.example.domain.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

/**
 * @author Chatzakis Nikolaos
 */
@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Optional<Role> findOne(String id) {
        return roleRepository.findOneById(id);
    }

    @Override
    public Role create(Role object) {
        return roleRepository.save(object);
    }

    @Override
    public Role update(String id, Role object) {
        roleRepository.findOneById(id)
                .orElseThrow(RuntimeException::new);

        if (!Objects.equals(id, object.getId())) delete(id);

        return create(object);
    }

    @Override
    public void delete(String id) {
        roleRepository.delete(id);
    }

    @Override
    public Optional<Role> findByName(String name) {
        return roleRepository.findByName(name);
    }
}
