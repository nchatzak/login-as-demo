package com.example.web.config;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.access.expression.SecurityExpressionHandler;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;

/**
 * @author Chatzakis Nikolaos
 */
@EnableWebSecurity
@Configuration
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final ApplicationContext applicationContext;
    private final RoleHierarchy roleHierarchy;

    public WebSecurityConfig(RoleHierarchy roleHierarchy, ApplicationContext applicationContext) {
        this.roleHierarchy = roleHierarchy;
        this.applicationContext = applicationContext;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .authorizeRequests()

                .expressionHandler(securityExpressionHandler())

                .antMatchers("/h2-console/**").permitAll()

                .antMatchers("/admin/**").hasAuthority("ROLE_ADMIN")

                .antMatchers("/mod/**").hasAuthority("ROLE_MODERATOR")

                .antMatchers("/user/**", "/index").hasAnyAuthority("ROLE_ADMIN", "ROLE_MODERATOR")

                .anyRequest().authenticated()

                .and()

                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/index")
                .failureUrl("/login-error")
                .permitAll()

                .and()

                .rememberMe()

                .and()

                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login");
    }

    protected SecurityExpressionHandler<FilterInvocation> securityExpressionHandler() {
        DefaultWebSecurityExpressionHandler defaultWebSecurityExpressionHandler = new DefaultWebSecurityExpressionHandler();

        defaultWebSecurityExpressionHandler.setApplicationContext(applicationContext);
        defaultWebSecurityExpressionHandler.setRoleHierarchy(roleHierarchy);

        return defaultWebSecurityExpressionHandler;
    }
}
