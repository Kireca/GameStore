package com.softuni.gamestore.services.user;

import com.softuni.gamestore.domain.dtos.UserLoginDTO;
import com.softuni.gamestore.domain.dtos.UserRegisterDTO;
import com.softuni.gamestore.domain.entities.User;
import com.softuni.gamestore.domain.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.softuni.gamestore.constants.Messages.*;

@Service
public class UserServiceImpl implements UserService {

    private User loggedInUser;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public String loginUser(String[] userLoginInfo) {

        final String email = userLoginInfo[1];
        final String password = userLoginInfo[2];

        final UserLoginDTO userLoginDTO = new UserLoginDTO(email, password);
        Optional<User> currentUser = this.userRepository.findFirstByEmail(userLoginDTO.getEmail());

        if (currentUser.isPresent() && this.loggedInUser == null && currentUser.get().getPassword().equals(userLoginDTO.getPassword())) {
            this.loggedInUser = this.userRepository.findFirstByEmail(userLoginDTO.getEmail()).get();
            return String.format(LOGIN_SUCCESSFUL_MESSAGE, this.loggedInUser.getFullName());
        }

        return USERNAME_OR_PASSWORD_NOT_VALID_MESSAGE;
    }

    @Override
    public String logout() {
        if (this.loggedInUser == null) {
            return CANNOT_LOGOUT_MESSAGE;
        }

        String username = this.loggedInUser.getFullName();
        this.loggedInUser = null;
        return String.format(LOGOUT_SUCCESSFUL_MESSAGE,username);

    }

    @Override
    public User getLoggedInUser() {
        return this.loggedInUser;
    }


    @Override
    public String registerUser(String[] userArgs) {
        final String email = userArgs[1];
        final String password = userArgs[2];
        final String confirmPassword = userArgs[3];
        final String fullName = userArgs[4];

        final UserRegisterDTO userRegisterDTO;

        try {
            userRegisterDTO = new UserRegisterDTO(email, password, confirmPassword, fullName);
        } catch (IllegalArgumentException exception) {
            return exception.getMessage();
        }

        User user = this.modelMapper.map(userRegisterDTO, User.class);

        if (this.userRepository.count() == 0) {
            user.setIsAdmin(true);
        }

        boolean doesUserExists = this.userRepository.existsUserByEmail(userRegisterDTO.getEmail());

        if (doesUserExists) {
            // throw new IllegalArgumentException(EMAIL_ALREADY_EXISTS_MESSAGE);
            return EMAIL_ALREADY_EXISTS_MESSAGE;
        }

        this.userRepository.save(user);
        return userRegisterDTO.successfulRegisterFormat();
    }
}
