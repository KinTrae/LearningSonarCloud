package com.mas.kinga.dtos;
import com.mas.kinga.models.OrderedGame;
import lombok.Data;

import java.util.List;

@Data
public class OrderedGamesDTO {
    private OrderDTO order;
    private List<OrderedGameDTO> orderedGames;


    public OrderedGamesDTO(List<OrderedGame> orderedGames) {
        this.orderedGames = orderedGames.stream().map(orderedGame -> new OrderedGameDTO(orderedGame.getId(), orderedGame.getGame(), orderedGame.getQuantity())).toList();
        this.order = new OrderDTO(orderedGames.get(0).getOrder());
    }

    public OrderedGamesDTO(){}
}
