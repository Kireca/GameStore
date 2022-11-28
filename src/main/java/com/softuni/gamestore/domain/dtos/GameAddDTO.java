package com.softuni.gamestore.domain.dtos;

import com.softuni.gamestore.domain.entities.Game;

import java.math.BigDecimal;
import java.time.LocalDate;

import static com.softuni.gamestore.constants.Messages.*;

public class GameAddDTO {

    private String title;

    private BigDecimal price;

    private float size;


    private String trailerYouTubeId;


    private String imageUrl;


    private String description;


    private LocalDate releaseDate;


    public GameAddDTO() {
    }

    public GameAddDTO(String title, BigDecimal price, float size, String trailerYouTubeId, String imageUrl, String description, LocalDate releaseDate) {
        setTitle(title);
        setPrice(price);
        setSize(size);
        setTrailerYouTubeId(trailerYouTubeId);
        setImageUrl(imageUrl);
        setDescription(description);
        setReleaseDate(releaseDate);
    }

    public void setTitle(String title) {

        throwExceptionIfIsNull(title);

        boolean isFirstLetterIsUpperCase = Character.isUpperCase(title.charAt(0));

        if (isFirstLetterIsUpperCase && title.length() >= 3 && title.length() <= 100) {
            this.title = title;
        } else {
            throw new IllegalArgumentException(INCORRECT_GAME_TITLE);
        }

    }

    public void setPrice(BigDecimal price) {
        throwExceptionIfIsNull(price);

        if (price.longValue() >= 0) {
            this.price = price;
        } else {
            throw new IllegalArgumentException(PRICE_CANNOT_BE_NEGATIVE);
        }
    }

    public void setSize(float size) {
        throwExceptionIfIsNull(size);

        if (size >= 0) {
            this.size = size;
        } else {
            throw new IllegalArgumentException(SIZE_CANNOT_BE_NEGATIVE);
        }

    }

    public void setTrailerYouTubeId(String trailerYouTubeId) {
        throwExceptionIfIsNull(trailerYouTubeId);

        if (trailerYouTubeId.length() == 11) {
            this.trailerYouTubeId = trailerYouTubeId;
        } else {
            throw new IllegalArgumentException(INCORRECT_TRAILER_ID);
        }
    }


    public void setImageUrl(String imageUrl) {
        throwExceptionIfIsNull(imageUrl);

        if (imageUrl.startsWith("http://") || imageUrl.startsWith("https://")) {
            this.imageUrl = imageUrl;
        } else {
            throw new IllegalArgumentException(INCORRECT_IMAGE_URL);
        }
    }


    public void setDescription(String description) {
        throwExceptionIfIsNull(description);

        if (description.length() >= 20) {
            this.description = description;
        } else {
            throw new IllegalArgumentException(INCORRECT_DESCRIPTION);
        }
    }

    public void setReleaseDate(LocalDate releaseDate) {
        throwExceptionIfIsNull(releaseDate);

        this.releaseDate = releaseDate;
    }


    private <T> void throwExceptionIfIsNull(T args) {
        if (isNull(args)) {
            throw new NullPointerException(CANNOT_BE_NULL);
        }
    }

    private <T> boolean isNull(T arg) {
        return arg == null;
    }

    public String getTitle() {
        return title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public float getSize() {
        return size;
    }

    public String getTrailerYouTubeId() {
        return trailerYouTubeId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public Game toGame() {
       return new Game(title, price, size, trailerYouTubeId, imageUrl, description, releaseDate);
    }
}
