package com.softuni.gamestore;

import com.softuni.gamestore.services.game.GameService;
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
    private final GameService gameService;

    public ConsoleRunner(UserService userService, GameService gameService) {
        this.userService = userService;
        this.gameService = gameService;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println(WAITING_FOR_INPUT_ARGS);
        String input = scanner.nextLine();

        while (!input.equals("close")) {
            String[] arguments = input.split("\\|");
            String command = arguments[0];
            final String message;

            switch (command) {
                case REGISTER_USER_COMMAND:

                    message = userService.registerUser(arguments);
                    System.out.println(message);
                    break;

                case LOGIN_USER:
                    message = userService.loginUser(arguments);
                    System.out.println(message);
                    break;


                case LOGOUT_USER:
                    message = userService.logout();
                    System.out.println(message);
                    break;

                case ADD_GAME_COMMAND:
                    message = gameService.addGame(arguments);
                    System.out.println(message);
                    break;
                default:
                    System.out.println(COMMAND_NOT_FOUND_MESSAGE);
            }

            input = scanner.nextLine();

        }


    }
}
