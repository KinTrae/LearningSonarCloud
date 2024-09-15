package com.mas.kinga.dtos;


import com.mas.kinga.models.Game;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;


@Data
@NoArgsConstructor
public class GameDTO {
    private Long id;
    private String name;
    private String description;
    private double price;
    private String image;
    private Integer quantity;
    private LocalDate releaseDate;


    public GameDTO(Game game){
        this.id = game.getId();
        this.name = game.getName();
        this.description = game.getDescription();
        this.price = game.getPrice();
        this.image = game.getImage();
        this.quantity = game.getQuantity();
        this.releaseDate = game.getReleaseDate();
    }

    public Game mapGameDtoToGame(){
        Game game = new Game();
        game.setId(this.id);
        game.setName(this.name);
        game.setImage(this.image);
        game.setPrice(this.price);
        game.setDescription(this.description);
        game.setQuantity(this.quantity);
        game.setReleaseDate(this.releaseDate);

        return game;
    }

}
