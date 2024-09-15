package com.mas.kinga.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.mas.kinga.validation.game.ValidGame;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
import java.util.*;

@Table(name = "games")
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ValidGame
@EqualsAndHashCode(exclude = "gameElements")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 2, max = 50)
    @Column(unique = true)
    private String name;

    @Size(min = 0, max = 500)
    private String description;

    @NotNull
    @DecimalMin(value = "0.0")
    private double price;

    private String image; //image url

    @NotNull
    private Integer quantity;

    @NotNull
    @PastOrPresent
    private LocalDate releaseDate;

    @NotNull
    @Enumerated(EnumType.STRING)
    @JsonIgnore
    private Set<GAME_TYPE> gameType = new HashSet<>();

    @JsonIgnore
    private Set<PLATFORM> platforms = new HashSet<>();//VIDEO

    @ElementCollection
    @CollectionTable(name = "game_license_keys", joinColumns = @JoinColumn(name = "game_id"))
    @MapKeyColumn(name = "license_key")
    @JsonIgnore
    private Map<String, Long> licenseKeys = new HashMap<>();

    //kategorie
    @ManyToMany
    @ToString.Exclude
    @JsonIgnore
    @JoinTable(
            name = "game_category",
            joinColumns = @JoinColumn(name = "game_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Category> categories = new ArrayList<>();

    //elementy gry
    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    @JsonIgnore
    private Set<GameElement> gameElements = new HashSet<>(); //BOARD_GAME

    //asocjacja many to many ze sklepem
    @ManyToMany(mappedBy = "games")
    @ToString.Exclude
    @JsonIgnore
    private Set<StationaryStore> stationaryStores = new HashSet<>();

}