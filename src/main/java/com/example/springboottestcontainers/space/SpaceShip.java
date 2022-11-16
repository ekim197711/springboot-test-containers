package com.example.springboottestcontainers.space;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SpaceShip {
    private String captain;
    private String model;
    private Integer fuelLeft;
}
