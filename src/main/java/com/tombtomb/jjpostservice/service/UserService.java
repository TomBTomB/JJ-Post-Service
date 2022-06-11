package com.tombtomb.jjpostservice.service;

import com.tombtomb.jjpostservice.model.User;

import java.util.List;
import java.util.UUID;

public interface UserService {

    User getLoggedUser();

    User getUser(UUID userId);

    List<User> searchUser(String searchString);

}
