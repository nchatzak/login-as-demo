package com.example.service;


import com.example.domain.entity.User;

import java.util.List;

/**
 * @author Chatzakis Nikolaos
 */
public interface UserService extends GenericService<User> {

    List<User> findByRole(String role);
}
