package com.mas.kinga.models;

import com.mas.kinga.validation.gameElement.ValidGameElement;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Table(name = "game_elements")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
@Entity
@Builder
@ValidGameElement
@EqualsAndHashCode(exclude = "game")
public class GameElement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;

    @NotBlank(message = "element name cannot be null")
    private String elementName;

    private String description;

    @Min(1)
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "game_id", nullable = false)
    @ToString.Exclude
    private Game game;
}
