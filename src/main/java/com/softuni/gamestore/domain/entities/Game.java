package com.softuni.gamestore.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "games")
public class Game extends BaseEntity {

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private BigDecimal price;
    @Column
    private float size;
    @Column(name = "trailer_youtube_id")
    private String trailerYouTubeId;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private LocalDate releaseDate;

    public Game() {
    }

    public Game(String title,
                BigDecimal price,
                float size,
                String trailerId,
                String imageUrl,
                String description,
                LocalDate releaseDate) {
        setTitle(title);
        setPrice(price);
        setSize(size);
        setTrailerId(trailerId);
        setImageUrl(imageUrl);
        setDescription(description);
        setReleaseDate(releaseDate);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTrailerId() {
        return trailerYouTubeId;
    }

    public void setTrailerId(String trailerId) {
        this.trailerYouTubeId = trailerId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageThumbnail) {
        this.imageUrl = imageThumbnail;
    }

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }
}