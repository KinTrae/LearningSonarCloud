package com.mas.kinga.models;

import com.mas.kinga.validation.order.ValidStatusDate;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Table(name = "orders")
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ValidStatusDate
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @CreationTimestamp
    private LocalDate orderDate;

    @DateTimeFormat
    private Date cancelDate;

    @Min(0)
    private Double sum;

    @DateTimeFormat
    private Date sendDate;

    //TODO validator na status
    private ORDERS_STATUS status;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<OrderedGame> orderedGames = new HashSet<>();

//    @Transient
//    public Double getSum(){
//        return  orderedGames.stream().map(og -> og.getPriceOfGame()*og.getQuantity()).mapToDouble(Double::valueOf).sum();
//    }

}
