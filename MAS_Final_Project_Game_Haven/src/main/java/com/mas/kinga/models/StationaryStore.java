package com.mas.kinga.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Table(name = "stationary_shop")
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StationaryStore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;

    @NotBlank(message = "Address cannot be null")
    private String address;

    @NotBlank(message = "Account cannot be null")
    private String accountNumber;

    @ManyToMany
    @JoinTable(
            name = "stationaryshop_game",
            joinColumns = @JoinColumn(name = "stationaryshop_id"),
            inverseJoinColumns = @JoinColumn(name = "game_id")
    )
    private Set<Game> games = new HashSet<>();

    @OneToMany(mappedBy = "stationaryStore")
    private Set<StationaryEmployee> employees = new HashSet<>();
}
