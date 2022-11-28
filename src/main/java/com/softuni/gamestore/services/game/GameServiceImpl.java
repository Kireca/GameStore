package com.softuni.gamestore.services.game;

import com.softuni.gamestore.domain.dtos.GameAddDTO;
import com.softuni.gamestore.domain.entities.Game;
import com.softuni.gamestore.domain.repositories.GameRepository;
import com.softuni.gamestore.services.user.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.softuni.gamestore.constants.Messages.GAME_SUCCESSFULLY_ADDED;
import static com.softuni.gamestore.constants.Messages.MUST_LOGGED_IN;

@Service
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;
    private final UserService userService;

    private final ModelMapper modelMapper = new ModelMapper();

    @Autowired
    public GameServiceImpl(GameRepository gameRepository, UserService userService) {
        this.gameRepository = gameRepository;
        this.userService = userService;
    }


    @Override
    public String addGame(String[] args) {
        if (this.userService.getLoggedInUser() != null && this.userService.getLoggedInUser().getIsAdmin()) {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            String title = args[1];
            BigDecimal price = new BigDecimal(args[2]);
            float size = Float.parseFloat(args[3]);
            String trailerYouTubeId = args[4];
            String imageUrl = args[5];
            String description = args[6];
            LocalDate localDate = LocalDate.parse(args[7], dateTimeFormatter);

            GameAddDTO gameAddDTO = new GameAddDTO(title, price, size, trailerYouTubeId, imageUrl, description, localDate);

            //  Game gameToSave = this.modelMapper.map(gameAddDTO, Game.class);

            Game gameToSave = gameAddDTO.toGame();
            this.gameRepository.save(gameToSave);

            return String.format(GAME_SUCCESSFULLY_ADDED, gameToSave.getTitle());

        }
        return MUST_LOGGED_IN;
    }

    @Override
    public String editGame(String[] args) {
        return null;
    }

    @Override
    public String deleteGame(String[] args) {
        return null;
    }
}
