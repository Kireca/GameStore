package com.softuni.gamestore.services.user;

import com.softuni.gamestore.domain.dtos.UserRegister;
import com.softuni.gamestore.domain.entities.User;
import com.softuni.gamestore.domain.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.softuni.gamestore.constants.Messages.EMAIL_ALREADY_EXISTS_MESSAGE;
import static com.softuni.gamestore.constants.Messages.SUCCESSFUL_REGISTER_USER;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void registerUser(String[] userArgs) {
        final String email = userArgs[1];
        final String password = userArgs[2];
        final String confirmPassword = userArgs[3];
        final String fullName = userArgs[4];
        final UserRegister userRegisterDTO = new UserRegister(email, password, confirmPassword, fullName);

        User user = this.modelMapper.map(userRegisterDTO, User.class);

        if (this.userRepository.count() == 0) {
            user.setIsAdmin(true);
        }


        boolean isUserFound = this.userRepository.findByEmail(userRegisterDTO.getEmail()).isPresent();

        if (isUserFound){
            throw new IllegalArgumentException(EMAIL_ALREADY_EXISTS_MESSAGE);
        }

        this.userRepository.save(user);
        System.out.printf(SUCCESSFUL_REGISTER_USER,user.getFullName());


    }




    @Override
    public String loginUser(String[] userLoginInfo) {


        return null;
    }
}
