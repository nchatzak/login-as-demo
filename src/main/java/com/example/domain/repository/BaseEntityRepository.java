package com.example.domain.repository;

import com.example.domain.entity.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

/**
 * @author Chatzakis Nikolaos
 */
@NoRepositoryBean
public interface BaseEntityRepository<T extends BaseEntity> extends JpaRepository<T, String> {

    Optional<T> findOneById(String id);
}