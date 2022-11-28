package com.softuni.gamestore.services.user;

import com.softuni.gamestore.domain.entities.User;

public interface UserService {
    String registerUser(String[] userArgs);

    String loginUser(String[] userLoginInfo);

    String logout();

    User getLoggedInUser();
}
