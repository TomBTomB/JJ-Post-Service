package com.tombtomb.jjpostservice.service.impl;

import com.tombtomb.jjpostservice.model.User;
import com.tombtomb.jjpostservice.repository.UserRepository;
import com.tombtomb.jjpostservice.service.UserService;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.representations.AccessToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getLoggedUser() {
        KeycloakPrincipal principal=(KeycloakPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        KeycloakSecurityContext session = principal.getKeycloakSecurityContext();
        AccessToken accessToken = session.getToken();

        User user = userRepository.findByUsername(accessToken.getPreferredUsername());

        if (user == null) {
            user = User.builder()
                    .username(accessToken.getPreferredUsername())
                    .avatar(accessToken.getPicture())
                    .displayName(accessToken.getPreferredUsername())
                    .bio(accessToken.getProfile())
                    .build();
            userRepository.save(user);
        }

        return user;
    }

    @Override
    public User getUser(UUID userId) {
        return userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User does not exist"));
    }

    @Override
    public List<User> searchUser(String searchString) {
        return userRepository.findAllByUsernameContainingOrDisplayNameContaining(searchString, searchString);
    }
}
