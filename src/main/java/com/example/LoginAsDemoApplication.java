package com.example;

import com.example.domain.entity.Role;
import com.example.domain.entity.User;
import com.example.service.RoleService;
import com.example.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collections;

@SpringBootApplication
public class LoginAsDemoApplication implements CommandLineRunner {

    private final RoleService roleService;
    private final UserService userService;

    public LoginAsDemoApplication(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    public static void main(String[] args) {
        SpringApplication.run(LoginAsDemoApplication.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        roleService.create(new Role("ROLE_ADMIN"));
        roleService.create(new Role("ROLE_MODERATOR"));

        final User admin = new User();
        admin.setEmail("admin@example.com");
        admin.setUsername("admin@example.com");
        admin.setPassword("$2a$04$aUBKid4ooqa2fKMRsWkxguqW8TWVMRzAW1.U4YLrsSzAHDkvl4szC");
        admin.setEnabled(true);
        admin.setRoles(Collections.singleton(
                roleService.findByName("ROLE_ADMIN").orElseThrow(RuntimeException::new))
        );

        final User mod1 = new User();
        mod1.setEmail("mod1@example.com");
        mod1.setUsername("mod1@example.com");
        mod1.setPassword("$2a$04$88gChUphsx/Ts.at33RrKOwRx2jy8qpuxsKOjfFgOPrYdTNtYt1NW");
        mod1.setEnabled(true);
        mod1.setRoles(Collections.singleton(
                roleService.findByName("ROLE_MODERATOR").orElseThrow(RuntimeException::new))
        );

        final User mod2 = new User();
        mod2.setEmail("mod2@example.com");
        mod2.setUsername("mod2@example.com");
        mod2.setPassword("$2a$04$Gshv3WYwycFHXgFHaK932uw0Fog442RVhuWSo2I.MMWKO/4l9bKxa");
        mod2.setEnabled(true);
        mod2.setRoles(Collections.singleton(
                roleService.findByName("ROLE_MODERATOR").orElseThrow(RuntimeException::new))
        );

        userService.create(admin);
        userService.create(mod1);
        userService.create(mod2);
    }
}
