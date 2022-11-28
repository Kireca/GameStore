package com.softuni.gamestore;

import com.softuni.gamestore.services.user.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

import static com.softuni.gamestore.constants.Commands.*;
import static com.softuni.gamestore.constants.Messages.COMMAND_NOT_FOUND_MESSAGE;
import static com.softuni.gamestore.constants.Messages.WAITING_FOR_INPUT_ARGS;

@Component
public class ConsoleRunner implements CommandLineRunner {
    Scanner scanner = new Scanner(System.in);
    private final UserService userService;

    public ConsoleRunner(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println(WAITING_FOR_INPUT_ARGS);
        String input = scanner.nextLine();

        while (!input.equals(END_COMMAND)) {
            String[] arguments = input.split("\\|");
            String command = arguments[0];

            switch (command) {
                case REGISTER_USER_COMMAND:
                    userService.registerUser(arguments);
                    break;

                case LOGIN_USER:
                    userService.loginUser(arguments);
                    System.out.println("successful login");

                    break;
                default:
                    System.out.println(COMMAND_NOT_FOUND_MESSAGE);
            }

          input = scanner.nextLine();

        }


    }
}
