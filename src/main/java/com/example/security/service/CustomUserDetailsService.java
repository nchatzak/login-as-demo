package com.example.security.service;

import com.example.domain.entity.User;
import com.example.domain.repository.UserRepository;
import com.example.security.domain.CustomUserDetails;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.function.Function;

/**
 * @author Chatzakis Nikolaos
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        final Function<User, CustomUserDetails> userToCustomUserDetails = user -> {
            final CustomUserDetails userDetails = new CustomUserDetails();

            BeanUtils.copyProperties(user, userDetails);

            return userDetails;
        };

        return userRepository
                .findByEmail(email)
                .map(userToCustomUserDetails)
                .orElseThrow(() -> new UsernameNotFoundException(email));
    }
}
