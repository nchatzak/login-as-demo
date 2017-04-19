package com.example.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.access.vote.RoleHierarchyVoter;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author Chatzakis Nikolaos
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    private final String ROLE_HIERARCHY = "ROLE_ADMIN > ROLE_MODERATOR";

    @Bean
    protected RoleHierarchyImpl roleHierarchy() {
        RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
        roleHierarchy.setHierarchy(ROLE_HIERARCHY);
        return roleHierarchy;
    }

    @Bean
    protected RoleVoter roleVoter() {
        return new RoleHierarchyVoter(roleHierarchy());
    }

    @Autowired
    protected void configureGlobal(
            AuthenticationManagerBuilder authenticationManagerBuilder,
            UserDetailsService customUserDetailsService,
            PasswordEncoder passwordEncoder
    ) throws Exception {
        authenticationManagerBuilder
                .userDetailsService(customUserDetailsService)
                .passwordEncoder(passwordEncoder);
    }
}
