package com.mas.kinga.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Table(name = "stationary_employees")
@Entity
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class StationaryEmployee extends Person{

    @NotNull
    @Min(value = 0)
    private double salary;

    @ManyToOne
    @JoinColumn(name = "stationaryshop_id")
    private StationaryStore stationaryStore;
}
