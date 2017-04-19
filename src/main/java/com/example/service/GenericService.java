package com.example.service;


import com.example.domain.entity.BaseEntity;

import java.util.Optional;

/**
 * @author Chatzakis Nikolaos
 */
public interface GenericService<T extends BaseEntity> {

    Optional<T> findOne(String id);

    T create(T object);

    T update(String id, T object);

    void delete(String id);
}
