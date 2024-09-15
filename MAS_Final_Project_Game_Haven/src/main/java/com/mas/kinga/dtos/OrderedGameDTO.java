package com.mas.kinga.dtos;

import com.mas.kinga.models.Game;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderedGameDTO {
    private Long id;
    private String name;
    private String description;
    private double price;
    private String image;
    private int numberOfOrderedGames;


    public OrderedGameDTO(long id, Game game, int numberOfOrderedGames){
        this.id = id;
        this.name = game.getName();
        this.description = game.getDescription();
        this.price = game.getPrice();
        this.image = game.getImage();
        this.numberOfOrderedGames = numberOfOrderedGames;
    }

    public OrderedGameDTO(){}


}
