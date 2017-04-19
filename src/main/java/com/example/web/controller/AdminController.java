package com.example.web.controller;

import com.example.domain.entity.User;
import com.example.security.sevice.CustomUserDetailsService;
import com.example.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Chatzakis Nikolaos
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final CustomUserDetailsService customUserDetailsService;
    private final AuthenticationManager authenticationManager;

    public AdminController(UserService userService, CustomUserDetailsService customUserDetailsService, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.customUserDetailsService = customUserDetailsService;
        this.authenticationManager = authenticationManager;
    }

    @GetMapping
    public String toAdmin(final Model model) {

        model.addAttribute("moderators", userService.findByRole("ROLE_MODERATOR"));

        return "admin";
    }

    @PostMapping("loginAs")
    public String loginAsMod(@RequestParam("modId") final String modId,
                             final HttpServletRequest request) {

        final User user = userService.findOne(modId)
                .orElseThrow(RuntimeException::new);

        final UserDetails userDetails = customUserDetailsService.loadUserByUsername(user.getEmail());
        final UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                userDetails, user.getPassword(), userDetails.getAuthorities());
        final Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        final SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authenticate);

        request.getSession(true).setAttribute("SPRING_SECURITY_CONTEXT", securityContext);

        return "redirect:/mod/";
    }
}
