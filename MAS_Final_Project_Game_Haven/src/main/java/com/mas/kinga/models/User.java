package com.mas.kinga.models;

import com.mas.kinga.validation.userDynamic.ValidUserDynamic;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.Length;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Table(name = "users")
@Entity
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ValidUserDynamic
public class User extends Person{

    @Column(unique = true)
    @NotBlank(message = "login cannot be blank")
    private String login;

    @Length(min = 3)
    @NotBlank(message = "password cannot be blank")
    private String password;

    @Column(unique = true)
    @NotBlank(message = "mail cannot be blank")
    private String email;

    @NotNull //TODO dodać walidację na enum
    private ROLE role;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "logs", joinColumns = @JoinColumn(name = "log_id"))
    @ToString.Exclude
    private List<String> logs;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private Set<Order> orders = new HashSet<>();

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private Set<GameReview> gameReviews = new HashSet<>();

}
