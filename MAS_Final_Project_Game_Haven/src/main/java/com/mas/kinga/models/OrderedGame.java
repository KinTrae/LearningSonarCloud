package com.mas.kinga.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "ordered_game")
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderedGame {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;

    @Min(1)
    private int quantity;
    private double priceOfGame;
    private String orderComment;

    @ManyToOne
    @JoinColumn(name = "Orders_Id", nullable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "Games_Id", nullable = false)
    private Game game;
}
