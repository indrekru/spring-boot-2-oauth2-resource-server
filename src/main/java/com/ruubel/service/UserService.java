package com.ruubel.service;

import com.ruubel.model.Role;
import com.ruubel.model.User;
import com.ruubel.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService {

    private static String ROLE_USER = "ROLE_USER";

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private RoleService roleService;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleService roleService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
    }

    public User createUser(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            user = new User(email, hashPassword(password), new ArrayList<>(), true);
            addRoleToUser(user, ROLE_USER);
            userRepository.save(user);
            return user;
        }
        return null;
    }

    private void addRoleToUser(User user, String roleStr) {
        Role role = roleService.findByRole(roleStr);
        if (role == null) {
            role = roleService.saveRole(roleStr);
        }
        user.getRoles().add(role);
    }

    private String hashPassword(String password) {
        return passwordEncoder.encode(password);
    }

    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = null;
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            currentUserName = authentication.getName();
        }
        User user = null;
        if (currentUserName != null) {
            user = findByEmail(currentUserName);
        }
        return user;
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
