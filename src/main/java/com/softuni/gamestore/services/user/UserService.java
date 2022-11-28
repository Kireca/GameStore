package com.softuni.gamestore.services.user;

public interface UserService {
    void registerUser(String[] userArgs);

    String loginUser(String[] userLoginInfo);
}
