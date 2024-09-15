package com.mas.kinga.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Table(name = "game_reviews")
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GameReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;

    @Transient
    private static int MAX_RATING = 10;

    @NotNull
    private int rating;

    private String comment;

    @CreatedDate
    private LocalDate reviewDate;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User users;

    @ManyToOne
    @JoinColumn(name = "game_id", nullable = false)
    private Game game;

}
